: <<'COMMENT'
git init
git add .
git commit -m "Initial BPM-APP commit"
git branch -M main
git remote add origin https://github.com/romeo0012/bpm-app.git
git branch -M main
git push -u origin main
COMMENT
git add -A
git commit --amend --no-edit
git push --force-with-lease origin main