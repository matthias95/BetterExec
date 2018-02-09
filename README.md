# BetterExec
I created a custom  [OpenHab 2](https://github.com/openhab)-binding for executing shell commands because the default exec-binding did not fit my needs. \
BetterExec is designed to work with a switch-item. 
It is possible to define different commands, depending if the switch is turned on or off. Commands are executed sequentially, one at a time within a FIFO queue. Currently there is a hard-coded 5 second timeout after which the command terminated, so the next command in the queue can be executed. 

## Usage
Example thing definition:\
`betterexec:command:rcsocket1 "rcsocket1" [oncommand="switchrcsocket 00000 0 1", offcommand="switchrcsocket 00000 0 0"]`

Example item definition:\
`Switch RCSocketSwitch1          "Socket 1"           {channel="betterexec:command:rcsocket1:execute"}`

