javac ./myMinHeap.java
javac ./CreateRuns.java
javac ./DistributeRuns.java
javac ./MergeRuns.java
rm *.runs
cat ./MobyDick.txt | java CreateRuns 10 | java MergeRuns 2