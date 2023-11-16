git remote add $1 git@github.com:boykush/$1.git
git fetch $1
git read-tree --prefix=$1/ $1/$2
git checkout -- .
git add .
git commit -m "add $1"
git merge -s subtree $1/$2 --allow-unrelated-histories
