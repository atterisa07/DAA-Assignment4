# DAA Assignment 4 — Dynamic Programming and String Algorithms

**Name:** Otegen Talshyn 
**Group:** SE-2525

## Project Description

This project implements and compares Dynamic Programming and String Search algorithms in Java.

The goal is to understand how different algorithms work and compare their performance using execution time and operation counters.

## Implemented Algorithms

### Edit Distance
1. Recursive Edit Distance
2. DP Table Edit Distance
3. Two Rows Edit Distance

### String Search
1. Naive Search
2. KMP Search with LPS array

## Technologies

- Java 17
- Maven
- JUnit 5
- Python 3.9
- Pandas
- Matplotlib

## How to Run

Run the main program:

```bash
mvn compile
java -cp target/classes Main
```

Run JUnit tests:

```bash
mvn test
```

Run experiments:

```bash
mvn compile
java -cp target/classes Experiment
```

Alternatively, run `Experiment.java` directly in IntelliJ IDEA.

Generate plots:

```bash
py -m pip install pandas matplotlib
py plots.py
```

## Experiment

The experiment compares algorithms using different input sizes and input types.

- Edit Distance sizes: 2, 4, 6, 8
- String Search sizes: 1000, 2000, 4000, 8000
- Each experiment is repeated three times.
- Results are saved in `results/results.csv`.

## Results

The project includes three plots:

- Edit Distance Operations
- String Search Comparisons
- String Search Execution Time

The plots are located in `results/plots/`.

## Conclusion

Recursive Edit Distance performs many repeated calculations. Dynamic Programming reduces this repeated work.

KMP uses the LPS array to avoid unnecessary character comparisons. It is useful for searching patterns in long texts.

## AI Use Acknowledgement

I used ChatGPT to help understand the assignment requirements, simplify the Java code, prepare tests, and organize the experiment and report. I reviewed the code and ran the experiments in IntelliJ IDEA.