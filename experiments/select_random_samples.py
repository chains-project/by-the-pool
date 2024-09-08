import pandas as pd

file_path = 'dataset/EQ.tsv'
df = pd.read_csv(file_path, sep='\t')

filtered_df = df.loc[(df['scope_1'] == 'main') & (df['scope_2'] == 'main')]
sampled_df = filtered_df.sample(n=30000)

output_path = 'random_numbers.txt'
adjusted_indexes = sampled_df.index - 2
adjusted_indexes.to_series().to_csv(output_path, index=False, header=False,lineterminator='\n')
