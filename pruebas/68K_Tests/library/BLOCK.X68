*-----------------------------------------------------------
* Title      : Block
* Written by : Emanuel Hegedus
* Date       : 2022
* Description: Calls to instantiate and call block funtions
*-----------------------------------------------------------

;------------------------------------------------------------------------------
;INPUT: A0-POINTER TO MEMORY SPACE, D0.W->PROCID, D0.L HIGH PART->ID
;OUTPUT: A0.W-BLOCK POINTER
;MODIFIES: NONE
;------------------------------------------------------------------------------
BLCREATE:   
            SWAP    D0
            JSR     DMMALLOC
            SWAP    D0
            MOVE.W  D0, (A0)+
            RTS
            
;------------------------------------------------------------------------------
;INPUT: A0-BLOCK POINTER, D0.L->VALUE TO PUSH(ADDRESS),
;D1.W->UPPER-NPARAM(0 IF NO PARAMETER, 1..N OTHERWISE)->LOWER-TYPE(V,C,P)
;
;
;------------------------------------------------------------------------------
BLPUSH:     MOVEM.L D2, -(A7)
            CMP.B   #'V', D1
            BEQ     .VAR
            CMP.B   #'C', D1
            MOVE.W  #4, D2
            MOVE.W  D1, -(A7)
            ROR.W   #8, D1                  ;GET UPPER TO LOWER
            MULU.W  D1, D2                  ;GET PARAMETER POSITION
            ADDI.W  #8, D2                  ;ADD 8 (PB+RETURN)
            MOVE.W  (A7)+, D1               ;RESTORE D1
            ADD.W   D2, A0                  ;GET POINTER IN POSITION
            MOVE.L  D0, (A0)                ;PUSH ADDRESS OF VALOR
.END        MOVEM.L (A7)+, D2               ;RESTORE MODIFIED REGISTERS
            RTS                             ;RETURN
.VAR        



*~Font name~Courier New~
*~Font size~10~
*~Tab type~0~
*~Tab size~8~
