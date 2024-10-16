# jNorm

## 1. org.apache.isis:isis-parent:2.0.0-M7

[jNorm file](1/jNorm/diffoscope.json) for org.apache.isis:isis-parent:2.0.0-M7.

DIFFERENT

Shows ordering difference only under `comments`.

## 2. dev.langchain4j:langchain4j:0.27.1

[jNorm file](2/jNorm/diffoscope.json) for dev.langchain4j:langchain4j:0.27.1.

DIFFERENT

Unnecessary type cast to Object.

## 3. org.apache.sling:org.apache.sling.feature.extension.unpack:0.4.0

SAME

Unnecessary type cast to Object[] twice.

## 4. dev.langchain4j:langchain4j:0.26.0

DIFFERENT

Multiple `jimple` files are generated, however only one pair of them mismatch.

```shell
diffoscope jNorm/reference/EmbeddingModelTextClassifier\$lambda_null_0__6.jimple  jNorm/rebuild/EmbeddingModelTextClassifier\$lambda_new_0__6.jimple
```

[jNorm file](4/jNorm/diffoscope.json) for dev.langchain4j:langchain4j:0.26.0.

Lamda is inlined in the new version.

## 5. org.apache.activemq:activemq-osgi-5.16.5

SAME

There is a change in `name` attribute (`adminACLs` -> `destination`).
But still, jNorm is able to normalize the output.

## 6. io.github.chains-project:maven-lockfile-github-action:5.2.0

DIFFEERENT

However, the change seems to be semantic.
`en-` to `en-US`

[jNorm file](6/jNorm/diffoscope.json) for io.github.chains-project:maven-lockfile-github-action:5.2.0.

## 7. org.apache.bcel:bcel:6.9.0

DIFFERENT

It was although a tests jar.
I expected no diff from jNorm because additional code seem to be compiler 
generated.

[jNorm file](7/jNorm/diffoscope.json) for org.apache.bcel:bcel:6.9.0.