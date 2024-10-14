# jNorm

## 1. org.apache.isis:isis-parent:2.0.0-M7

[jNorm file](1/jNorm/diffoscope.json) for org.apache.isis:isis-parent:2.0.0-M7.

DIFFERENT

Shows ordering difference only under `comments`.

## 2. dev.langchain4j:langchain4j:0.27.1

[jNorm file](2/jNorm/diffoscope.json) for dev.langchain4j:langchain4j:0.27.1.

DIFFERENT

## 3. org.apache.sling:org.apache.sling.feature.extension.unpack:0.4.0

SAME

## 4. dev.langchain4j:langchain4j:0.26.0

DIFFERENT

Multiple `jimple` files are generated, however only one pair of them mismatch.

```shell
diffoscope jNorm/reference/EmbeddingModelTextClassifier\$lambda_null_0__6.jimple  jNorm/rebuild/EmbeddingModelTextClassifier\$lambda_new_0__6.jimple
```

[jNorm file](4/jNorm/diffoscope.json) for dev.langchain4j:langchain4j:0.26.0.



