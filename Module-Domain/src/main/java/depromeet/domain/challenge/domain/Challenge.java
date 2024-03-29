package depromeet.domain.challenge.domain;

import static java.util.stream.Collectors.toList;

import depromeet.domain.category.domain.Category;
import depromeet.domain.challenge.domain.keyword.ChallengeKeywords;
import depromeet.domain.challenge.exception.ChallengeCannotBeDeletedAfterStartException;
import depromeet.domain.challenge.exception.ChallengeCannotBeUpdatedAfterStartException;
import depromeet.domain.config.BaseTime;
import depromeet.domain.keyword.domain.Keyword;
import depromeet.domain.rule.domain.ChallengeRule;
import depromeet.domain.user.domain.User;
import depromeet.domain.userchallenge.domain.UserChallenge;
import depromeet.domain.userchallenge.exception.ChallengeIsFullException;
import depromeet.domain.userchallenge.exception.ChallengeIsStartedException;
import depromeet.domain.userchallenge.exception.DuplicateParticipationException;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@TypeDef(name = "json", typeClass = JsonType.class)
public class Challenge extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long id;

    @Embedded private ChallengeCategories challengeCategories;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private Image imgUrl;

    @Enumerated(EnumType.STRING)
    private ChallengeStatusType status;

    @Embedded private ChallengeKeywords challengeKeywords;

    @Column(name = "available_count", nullable = false)
    private Integer availableCount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

    @OneToMany(mappedBy = "challenge")
    private List<UserChallenge> userChallenges;

    @Column(name = "challenge_rule")
    @OneToMany(
            mappedBy = "challenge",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private List<ChallengeRule> challengeRules;

    @Embedded private Duration duration;

    /** 생성 메서드 */
    public static Challenge createChallenge(
            String title,
            int price,
            ChallengeKeywords challengeKeywords,
            int availableCount,
            User user,
            List<ChallengeRule> challengeRules,
            ChallengeCategories challengeCategories,
            Duration duration) {
        return Challenge.builder()
                .title(title)
                .price(price)
                .challengeKeywords(challengeKeywords)
                .availableCount(availableCount)
                .status(ChallengeStatusType.RECRUITING)
                .createdBy(user)
                .challengeRules(challengeRules)
                .challengeCategories(challengeCategories)
                .duration(duration)
                .build();
    }

    /** 비즈니스 메서드 */
    public void addRules(List<ChallengeRule> rules) {
        challengeRules.addAll(rules);
        rules.forEach(rule -> rule.setChallenge(this));
    }

    public void addCategories(List<Category> categories) {
        challengeCategories.addAll(this, categories);
    }

    public void addKeywords(List<Keyword> keywords) {
        challengeKeywords.addAll(this, keywords);
    }

    private String parsingTitle() {
        if (this.title.contains("커피")) return FoodDetailType.COFFEE.name();
        else if (this.title.contains("배달")) return FoodDetailType.DELIVERY.name();
        return "";
    }

    public String getDefaultImage(String category) {
        String foodType = parsingTitle();
        if (!foodType.isBlank()) return FoodDetailType.valueOf(foodType).getDefaultUrl();
        return CategoryType.valueOf(category).getDefaultUrl();
    }

    private String getThumb(String category) {
        String foodType = parsingTitle();
        if (!foodType.isBlank()) return FoodDetailType.valueOf(foodType).getThumbUrl();
        return CategoryType.valueOf(category).getThumbUrl();
    }

    public void addImage(String defaultUrl, String category) {
        String thumbUrl = getThumb(category);
        this.imgUrl = new Image(defaultUrl, thumbUrl);
    }

    public boolean isParticipateChallengeUser(String socialId) {
        return this.userChallenges.stream()
                .anyMatch(
                        userChallenge ->
                                userChallenge.getUser().getSocial().getId().equals(socialId));
    }

    public boolean isNotWrittenBy(String createdBy) {
        return !this.createdBy.getSocial().getId().equals(createdBy);
    }

    private boolean isStarted(final LocalDate localdate) {
        return localdate.isBefore(LocalDate.now());
    }

    public void validateDelete(final LocalDate localdate) {
        if (isStarted(localdate)) throw ChallengeCannotBeDeletedAfterStartException.EXCEPTION;
    }

    public void validateUpdate(final LocalDate localdate) {
        if (isStarted(localdate)) throw ChallengeCannotBeUpdatedAfterStartException.EXCEPTION;
    }

    public void validateDuplicatedParticipation(String socialId) {
        if (isParticipateChallengeUser(socialId)) throw DuplicateParticipationException.EXCEPTION;
    }

    public void validateCurrentUserCount() {
        if (this.userChallenges.size() + 1 > this.availableCount)
            throw ChallengeIsFullException.EXCEPTION;
    }

    public void validateInToChallenge(final LocalDate localDate) {
        if (isStarted(localDate)) throw ChallengeIsStartedException.EXCEPTION;
    }

    public boolean isRecruiting(final LocalDate localDate) {
        if (isStarted(localDate)) return false;
        return true;
    }

    public void update(String title, int price, String imgUrl, int availableCount) {
        this.title = title;
        this.price = price;
        this.imgUrl = new Image(imgUrl, this.imgUrl.getThumbUrl());
        this.availableCount = availableCount;
    }

    public void updateChallengeCategories(List<Category> categories) {
        challengeCategories.clear();
        addCategories(categories);
    }

    public void updateKeywords(List<Keyword> keywords) {
        challengeKeywords.clear();
        addKeywords(keywords);
    }

    public void updateChallengeRules(List<ChallengeRule> rules) {
        challengeRules.clear();
        addRules(rules);
    }

    public void updateDuration(int period, LocalDate startAt, LocalDate endAt) {
        this.duration = new Duration(period, startAt, endAt);
    }

    public List<String> getChallengeRuleContents() {
        return challengeRules.stream().map(ChallengeRule::getContent).collect(toList());
    }

    private boolean isApproachingDeadline(final LocalDate startDate) {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate DeadlineStart = startDate.minusDays(4);
        return currentDate.isAfter(DeadlineStart) && currentDate.isBefore(startDate);
    }

    private boolean isBeforeApproachingDeadline(final LocalDate startDate) {
        final LocalDate currentDate = LocalDate.now();
        final LocalDate comingSoonStart = startDate.minusDays(8);
        final LocalDate comingSoonEnd = startDate.minusDays(3);

        return currentDate.isAfter(comingSoonStart) && currentDate.isBefore(comingSoonEnd);
    }

    private long getDiffDay(final LocalDate createdAt) {
        return ChronoUnit.DAYS.between(LocalDate.now(), createdAt);
    }

    public String checkStatusInChallengeDetail(final LocalDate createdAt) {
        if (isBeforeApproachingDeadline(createdAt))
            return String.format("%d%s", getDiffDay(createdAt), StatusType.AFTER_DAY.getName());
        // 리팩토링 예정
        if (isApproachingDeadline(this.getDuration().getStartAt()))
            return StatusType.APPROACHING_DEADLINE.getName();

        return StatusType.NOTHING.getName();
    }

    public void open() {
        if (isRecruiting()) {
            status = ChallengeStatusType.PROCEEDING;
            userChallenges.stream().forEach(userChallenge -> userChallenge.startChallenge());
        }
    }

    public void close() {
        if (isProceeding()) {
            status = ChallengeStatusType.CLOSE;
            userChallenges.stream().forEach(userChallenge -> userChallenge.endChallenge());
        }
    }

    public Boolean isProceeding() {
        return (status == ChallengeStatusType.PROCEEDING);
    }

    public Boolean isRecruiting() {
        return (status == ChallengeStatusType.RECRUITING);
    }

    public boolean isEnd() {
        return status == ChallengeStatusType.CLOSE;
    }
}
