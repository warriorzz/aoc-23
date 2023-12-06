$cookie = Get-Content .\session.cookie -Raw
$url = "https://adventofcode.com/2023/day/" + $args[0] + "/input"
$input = curl.exe --cookie $cookie -X GET $url
$path = ".\input\day-" + $args[0] + ".txt"
Set-Content -Path $path -Value $input