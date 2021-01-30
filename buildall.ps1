Push-Location .\generator

.\generateCode.ps1

Pop-Location

# Expose environment variables

foreach ($line in Get-Content .\variables.conf) {
    $line = $line.Trim()
    if ($line.StartsWith('export')) {
        $line = $line.Replace('export', '').Trim()
        $vals = $line.Split('=', 2)
        [System.Environment]::SetEnvironmentVariable($vals[0], $vals[1], [System.EnvironmentVariableTarget]::Process)
    }
}

mvn -B package --file sdk/pom.xml