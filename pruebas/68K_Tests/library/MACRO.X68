*-----------------------------------------------------------
* Title      : MACROs
* Written by : Emanuel Hegedus
* Date       : 2022
* Description: Macros for usefull operations
*-----------------------------------------------------------
            OPT     $1000
            OPT     MEX

;A AND B, VALUE IN D1
CLRH:       MACRO
            SWAP    \1
            CLR.W   \1
            SWAP    \1
            ENDM

ANDM:       MACRO
            MOVE.L  D0, -(A7)
            MOVE.L  \1, D0
            MOVE.L  \2, D1
            AND.L   D0, D1
            MOVE.L  (A7)+, D0
            ENDM

;A OR B, VALUE IN D1

ORM:        MACRO
            MOVE.L  D0, -(A7)
            MOVE.L  \1, D0
            MOVE.L  \2, D1
            OR.L    D0, D1
            MOVE.L  (A7)+, D0
            ENDM
            
;NOT A, VALUE IN D1

NOTM:       MACRO
            MOVE.L  \1, D1
            NOT.L   D1
            ENDM
            
;A + B, VALUE IN D1

ADDM:       MACRO
            MOVE.L  D0, -(A7)
            MOVE.L  \1, D0
            MOVE.L  \2, D1
            ADD.L   D0, D1
            MOVE.L  (A7)+, D0
            ENDM
            
;A - B, VALUE IN D1

SUBM:       MACRO
            MOVE.L  D0, -(A7)
            MOVE.L  \1, D0
            MOVE.L  \2, D1
            SUB.L   D1, D0
            MOVE.L  D0, D1
            ENDM
            
;A / B, VALUE IN D1

DIVM:       MACRO
            MOVE.L  D0, -(A7)
            MOVE.L  \1, D1
            MOVE.L  \2, D0
            DIVS    D1, D0                  ; 68K ONLY SUPPORTS WORD DIVISION
            MOVE.L  D0, D1
            MOVE.L  (A7)+, D0
            ENDM
            
;A MOD B, VALUE IN D1

MODM:       MACRO
            MOVE.L  D0, -(A7)
            MOVE.L  \1, D1
            MOVE.L  \2, D0
            DIVU    D1, D0
            CLR.W   D0                      ; CLEAR LOWER PART
            SWAP    D0                      ; MODULUS TO LOWER (UPPER IS CLEAR)
            MOVE.L  D0, D1
            MOVE.L  (A7)+, D0
            ENDM
            
;A * B, VALUE IN D1

MULTM:      MACRO
            MOVE.L  D0, -(A7)
            MOVE.L  \1, D0
            MOVE.L  \2, D1
            MULU    D0, D1
            MOVE.L  (A7)+, D0
            ENDM
            
;-A, VALUE IN D1

NEGM:       MACRO
            MOVE.L  \1, D1
            NEG.L   D1
            ENDM
            









*~Font name~Courier New~
*~Font size~10~
*~Tab type~0~
*~Tab size~8~
