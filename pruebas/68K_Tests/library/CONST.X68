*-----------------------------------------------------------
* Title      : Constants
* Written by : Emanuel Hegedus
* Date       : 2022
* Description: Constants usefull to all files
*-----------------------------------------------------------

MINIT   EQU $1000

;INSTRUCTION TYPE CONSTANTS (AS ENUMERATED IN THEIR ENUM)
;-----------------------------------------------------------
CLONE   EQU 0
ADD     EQU 1
SUB     EQU 2
MUL     EQU 3
DIV     EQU 4
MOD     EQU 5
NEG     EQU 6
INCR    EQU 7
DECR    EQU 8
AND     EQU 9
OR      EQU 10
NOT     EQU 11
EXP     EQU 12
ASSIG   EQU 13
SKIP    EQU 14
GOTO    EQU 15
LT      EQU 16
LE      EQU 17
GT      EQU 18
GE      EQU 19
EQ      EQU 20
NEQ     EQU 21
INIT    EQU 22
CALL    EQU 23
RETURN  EQU 24
SIMPLE  EQU 25
COMPLEX EQU 26
READ    EQU 27
PRINT   EQU 28
PRINTLN EQU 29
;------------------------------------------------------------------------------


; --- DYNAMIC MEMORY CONSTANTS ------------------------------------------------

DMMBSIZE    EQU     1024                    ; BLOCK SIZE (BYTES). POWER OF TWO
DMMBNUM     EQU     128                     ; NUMBER OF BLOCKS
DMMSYSID    EQU     0                       ; SYSTEM OWNER ID (MUST BE 0)
DMMPRCID    EQU     -1                      ; -1 SO WE CAN USE 0
PARSPACE    EQU     DMMBSIZE/2-10           ; TEMPORARELY HALF PARAMETERS
VARSPACE    EQU     DMMBSIZE-PARSPACE       ; WE SUBSTRACT PARAMETER SPACE AND
                                            ; OTHER VALUES (RETURN, BP, DISP)


; --- TP CONSTANTS ------------------------------------------------------------

VARSIZE     EQU     10*4

; USED FOR OFFSET PURPOUSES, AS GETTING THE CORRECT VALUE WHEN CHECKING THE TP 

DISP        DS.L    10                      ; DISP allows 10 as max profundity ([0-9])








*~Font name~Courier New~
*~Font size~10~
*~Tab type~0~
*~Tab size~8~
