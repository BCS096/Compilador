        INCLUDE  library/MACRO.X68
        INCLUDE  library/CONST.X68
        INCLUDE  library/VAR.X68
;-----------------------------------------------------------
;-----------------------------------------------------------
; Initial program lines, main                               
;-----------------------------------------------------------
; Labels to memory space reserved for variables:            
;-----------------------------------------------------------
Variable0        DC.B 'Introduce un numero entre 0 y 9: ',0
Variable1        DS.L 1
Variable2        DS.L 1
Variable3        DS.L 1
Variable4        DS.L 1
Variable5        DS.L 1
Variable6        DS.L 1
Variable7        DS.L 1
Variable8        DC.B 'El numero era menor que 5',0
Variable9        DC.B 'El numero era mayor o igual a 5',0
Variable10        DC.B 'Linea de final',0
SParam0        DS.B 128
        DC.B 0
SParam1        DS.B 128
        DC.B 0
SParam2        DS.B 128
        DC.B 0
SParam3        DS.B 128
        DC.B 0
SParam4        DS.B 128
        DC.B 0
Param0        DS.L 1
Param1        DS.L 1
Param2        DS.L 1
Param3        DS.L 1
Param4        DS.L 1
Param5        DS.L 1
Param6        DS.L 1
Param7        DS.L 1
Param8        DS.L 1
Param9        DS.L 1
;-----------------------------------------------------------
        DS.W 0
;-----------------------------------------------------------
globals:
; Instruction of type: CALL
;Operator 1 -> 0

        JMP main ; Declared all globals
main:                                                       
        MOVE.L A7, A6
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 0

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L D0, A0 ; Param gest
        LEA Variable0, A1 ; Param gest
.loop99:
        MOVE.B (A0)+, (A1)+ 
        TST.B (A0) 
        BNE .loop99
        MOVE.B #0, (A1)+ 
; Instruction of type: PRINT
;Operator 1 -> 0

        LEA        Variable0, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: READ
;, Store in -> 1

        MOVE.L #5, D0 ; Prepare read
        TRAP #15 ; Expect input
        AND.L #$00FF, D1 ; Mask upper word (we read char = 2 bytes)
        MOVE.L D1, (Variable1) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 2

        MOVE.L #48, D0 ; Load variable
        MOVE.L D0, (Variable2) ; Store local variable
; Instruction of type: SUB
;Operator 1 -> 1, Operator 2 -> ReferenceOp(ref: 2), Store in -> 3

        MOVE.L (Variable1), D0 ; Load local variable
        MOVE.L (Variable2), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L D1, (Variable3) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 4

        MOVE.L (Variable3), D0 ; Load local variable
        MOVE.L D0, (Variable4) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 5

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L D0, (Variable5) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 6

        MOVE.L #5, D0 ; Load variable
        MOVE.L D0, (Variable6) ; Store local variable
; Instruction of type: IFLT
;Operator 1 -> 5, Operator 2 -> ReferenceOp(ref: 6), Store in -> 0

        MOVE.L (Variable5), D0 ; Load local variable
        MOVE.L (Variable6), D1 ; Load local variable
        CMP.L D1, D0
        BGE Label0
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable7) ; Store local variable
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label1
; Instruction of type: SKIP
;, Store in -> 0

Label0:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable7) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label1:
; Instruction of type: IFEQ
;Operator 1 -> 7, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L (Variable7), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BNE Label2
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 8

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L D0, A0 ; Param gest
        LEA Variable8, A1 ; Param gest
.loop98:
        MOVE.B (A0)+, (A1)+ 
        TST.B (A0) 
        BNE .loop98
        MOVE.B #0, (A1)+ 
; Instruction of type: PRINTLN
;Operator 1 -> 8

        LEA        Variable8, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
        MOVE.B #11, D0 ; Next line prepare
        MOVE.L #$00FF, D1 ; Request current coordinates
        TRAP #15 ; Get them in D1.L (HIGH=COL, LOW = ROW)
        AND.L #$00FF, D1 ; We want always column = 0
        ADDQ.B #1, D1 ; We increment the current row by 1
        TRAP #15 ; Set new coordinates (next line ready)
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label3
; Instruction of type: SKIP
;, Store in -> 0

Label2:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 9

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L D0, A0 ; Param gest
        LEA Variable9, A1 ; Param gest
.loop97:
        MOVE.B (A0)+, (A1)+ 
        TST.B (A0) 
        BNE .loop97
        MOVE.B #0, (A1)+ 
; Instruction of type: PRINTLN
;Operator 1 -> 9

        LEA        Variable9, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
        MOVE.B #11, D0 ; Next line prepare
        MOVE.L #$00FF, D1 ; Request current coordinates
        TRAP #15 ; Get them in D1.L (HIGH=COL, LOW = ROW)
        AND.L #$00FF, D1 ; We want always column = 0
        ADDQ.B #1, D1 ; We increment the current row by 1
        TRAP #15 ; Set new coordinates (next line ready)
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 10

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L D0, A0 ; Param gest
        LEA Variable10, A1 ; Param gest
.loop96:
        MOVE.B (A0)+, (A1)+ 
        TST.B (A0) 
        BNE .loop96
        MOVE.B #0, (A1)+ 
; Instruction of type: PRINT
;Operator 1 -> 10

        LEA        Variable10, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: SKIP
;, Store in -> 0

Label3:
; Instruction of type: RETURN
;Operator 1 -> 0

        SIMHALT
        END globals