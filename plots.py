
import os
import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("results/results.csv")
os.makedirs("results/plots", exist_ok=True)

data = df.groupby(
    ["algorithm", "input", "n"],
    as_index=False
).median(numeric_only=True)

dp = data[data["input"] == "different"]
for name in ["Recursive", "Table", "TwoRows"]:
    part = dp[dp["algorithm"] == name]
    plt.plot(part["n"], part["operations"], marker="o", label=name)

plt.yscale("log")
plt.xlabel("String length (characters)")
plt.ylabel("Operations (log scale)")
plt.title("Edit Distance Operations")
plt.legend()
plt.grid(True)
plt.savefig("results/plots/edit_distance.png")
plt.close()

search = data[data["input"] == "worst"]
for name in ["Naive", "KMP"]:
    part = search[search["algorithm"] == name]
    plt.plot(part["n"], part["operations"], marker="o", label=name)

plt.xlabel("Text length (characters)")
plt.ylabel("Character comparisons")
plt.title("String Search Comparisons")
plt.legend()
plt.grid(True)
plt.savefig("results/plots/search_operations.png")
plt.close()

for name in ["Naive", "KMP"]:
    part = search[search["algorithm"] == name]
    plt.plot(part["n"], part["time_ms"], marker="o", label=name)

plt.xlabel("Text length (characters)")
plt.ylabel("Median time (ms)")
plt.title("String Search Execution Time")
plt.legend()
plt.grid(True)
plt.savefig("results/plots/search_time.png")
plt.close()

print("3 plots created successfully!")
