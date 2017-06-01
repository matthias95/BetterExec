# BetterExec
I created a custom  [OpenHab 2](https://github.com/openhab)-binding for executing shell commands because the default exec-binding did not fit my needs. \
BetterExec is desingned to work with a switch-item. 
It is possible to define different commands, depeding if the switch is turned on or off. 

## Usage
Example thing definition:\
`betterexec:command:rcsocket1 "rcsocket1" [oncommand="switchrcsocket 00000 0 1", offcommand="switchrcsocket 00000 0 0"]`

Example item definition:\
`Switch RCSocketSwitch1          "Socket 1"           {channel="betterexec:command:rcsocket1:execute"}`


