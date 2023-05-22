#!/bin/sh

# 1. 변경된 파일들 이름만 추출하여 저장
stagedFiles=$(git diff --staged --name-only)
# 2. SpotlessApply 실행
echo "Running spotlessApply. Formatting code..."
./gradlew spotlessApply --daemon
# 3. 변경사항이 발생한 파일들 다시 git add
for file in $stagedFiles; do
  if test -f "$file"; then
    git add "$file"
  fi
done
