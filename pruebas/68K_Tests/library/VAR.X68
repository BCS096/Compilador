*-----------------------------------------------------------
* Title      : Variables
* Written by : Emanuel Hegedus
* Date       : 2022
* Description: Variables usefull to all files
*-----------------------------------------------------------
; --- DYNAMIC MEMORY VARIABLES ------------------------------------------------

            ORG     (*+DMMBSIZE-1)&-DMMBSIZE    ; ALIGN TO DMMBSIZE
DMMDATA     DS.B    DMMBSIZE*DMMBNUM            ; DYNAMIC MEMORY ALLOCATION SPACE
            DS.W    0                           ; JUST IN CASE (SIZE IS POW OF TWO)






*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~4~
