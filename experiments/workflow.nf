process diffoscope {
    errorStrategy 'ignore'
    cpus Runtime.getRuntime().availableProcessors()

    input:
    tuple val(path1), val(path2)

    script:
    """
    echo "Comparing ${path1} and ${path2}"
    diffoscope ${path1} ${path2}
    """
}

workflow {
    Channel
    .fromPath('../paths.csv', relative: true)
    .splitCsv(header: true)
    .map { row -> tuple(row.path1, row.path2) } \
    | diffoscope
}
