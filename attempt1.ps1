javac ./myMinHeap.java
javac ./CreateRuns.java
javac ./DistributeRuns.java
javac ./MergeRuns.java
rm *.runs
cat ./MobyDick.txt | java CreateRuns | java MergeRuns 3 > stuff.sorted
# cat ./MobyDick.txt | java CreateRuns 10
