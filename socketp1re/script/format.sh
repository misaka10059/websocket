#dos格式转unix
for file in `ls *.sh`
do
	dos2unix ${file}
done