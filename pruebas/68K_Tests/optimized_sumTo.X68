        INCLUDE  library/MACRO.X68
        INCLUDE  library/CONST.X68
        INCLUDE  library/VAR.X68
;-----------------------------------------------------------
;-----------------------------------------------------------
; Initial program lines, main                               
;-----------------------------------------------------------
; Labels to memory space reserved for variables:            
;-----------------------------------------------------------
Variable0        DS.L 1
Variable1        DC.B 'Sorry, no String found at given variable',0
Variable2        DS.L 1
Variable3        DS.L 1
Variable4        DS.L 1
Variable5        DS.L 1
Variable6        DS.L 1
Variable7        DS.L 1
Variable8        DS.L 1
Variable9        DS.L 1
Variable10        DS.L 1
Variable11        DS.L 1
Variable12        DC.B 'Sorry, no String found at given variable',0
Variable13        DC.B 'Sorry, no String found at given variable',0
Variable14        DC.B 'Sorry, no String found at given variable',0
Variable15        DS.L 1
Variable16        DS.L 1
Variable17        DS.L 1
Variable18        DC.B 'Introduce un numero entre 0 y 9: ',0
Variable19        DS.L 1
Variable20        DS.L 1
Variable21        DS.L 1
Variable22        DS.L 1
Variable23        DC.B 'La suma de 1 hasta tu numero es: ',0
Variable24        DS.L 1
Variable25        DC.B 'koalas',0
Variable26        DS.L 1
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
; Instruction of type: SKIP
;, Store in -> 0

        JMP main ; Declared all globals
Label0:
; Instruction of type: PMB
;, Store in -> 0

        LEA DISP, A0
        MOVE.L 8(A0), -(A7)
        MOVE.L A6, -(A7)
        MOVE.L A7, A6 ;BP = SP
        MOVE.L A6, 4(A7) ; DISP(prof) = BP
        MOVE.L A6, D0 ; D0=EBP
        MOVE.L (Param0), (Variable0) ; Param gest
        LEA SParam0, A0 ; Param gest
        LEA Variable1, A1 ; Param gest
.loop0:
        MOVE.B (A0)+, (A1)+ 
        TST.B (A0) 
        BNE .loop0
        MOVE.B #0, (A1)+ 
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 2

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable2) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 3

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable3) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 4

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable4) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 5

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable5) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label1:
; Instruction of type: CLONE
;Operator 1 -> 5, Store in -> 6

        MOVE.L (Variable5), D0 ; Load local variable
        MOVE.L D0, (Variable6) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L (Variable0), D0 ; Load local param
        MOVE.L D0, (Variable7) ; Store local variable
; Instruction of type: IFLE
;Operator 1 -> 5, Operator 2 -> ReferenceOp(ref: 0), Store in -> 0

        MOVE.L (Variable5), D0 ; Load local variable
        MOVE.L (Variable0), D1 ; Load local param
        CMP.L D1, D0
        BGT Label2
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 8

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable8) ; Store local variable
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label3
; Instruction of type: SKIP
;, Store in -> 0

Label2:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 8

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable8) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label3:
; Instruction of type: IFEQ
;Operator 1 -> 8, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L (Variable8), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BNE Label4
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 9

        MOVE.L (Variable3), D0 ; Load local variable
        MOVE.L D0, (Variable9) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 5, Store in -> 10

        MOVE.L (Variable5), D0 ; Load local variable
        MOVE.L D0, (Variable10) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 3, Operator 2 -> ReferenceOp(ref: 5), Store in -> 11

        MOVE.L (Variable3), D0 ; Load local variable
        MOVE.L (Variable5), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, (Variable11) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 3, Operator 2 -> ReferenceOp(ref: 5), Store in -> 3

        MOVE.L (Variable3), D0 ; Load local variable
        MOVE.L (Variable5), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, (Variable3) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 1, Store in -> 12

        LEA Variable1, A0 ; Load local param
        MOVE.L A0, D0 ; Load it to register 
        MOVE.L D0, A0 ; Param gest
        LEA Variable12, A1 ; Param gest
.loop99:
        MOVE.B (A0)+, (A1)+ 
        TST.B (A0) 
        BNE .loop99
        MOVE.B #0, (A1)+ 
; Instruction of type: CLONE
;Operator 1 -> 1, Store in -> 13

        LEA Variable1, A0 ; Load local param
        MOVE.L A0, D0 ; Load it to register 
        MOVE.L D0, A0 ; Param gest
        LEA Variable13, A1 ; Param gest
.loop98:
        MOVE.B (A0)+, (A1)+ 
        TST.B (A0) 
        BNE .loop98
        MOVE.B #0, (A1)+ 
; Instruction of type: CLONE
;Operator 1 -> 1, Store in -> 14

        LEA Variable1, A0 ; Load local param
        MOVE.L A0, D0 ; Load it to register 
        MOVE.L D0, A0 ; Param gest
        LEA Variable14, A1 ; Param gest
.loop97:
        MOVE.B (A0)+, (A1)+ 
        TST.B (A0) 
        BNE .loop97
        MOVE.B #0, (A1)+ 
; Instruction of type: PRINT
;Operator 1 -> 14

        LEA        Variable14, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 15

        MOVE.L (Variable3), D0 ; Load local variable
        MOVE.L D0, (Variable15) ; Store local variable
; Instruction of type: PRINTLN
;Operator 1 -> 15

        MOVE.L (Variable15), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
        MOVE.B #11, D0 ; Next line prepare
        MOVE.L #$00FF, D1 ; Request current coordinates
        TRAP #15 ; Get them in D1.L (HIGH=COL, LOW = ROW)
        AND.L #$00FF, D1 ; We want always column = 0
        ADDQ.B #1, D1 ; We increment the current row by 1
        TRAP #15 ; Set new coordinates (next line ready)
; Instruction of type: ADD
;Operator 1 -> 5, Operator 2 -> LiteralOp(value: 1), Store in -> 16

        MOVE.L (Variable5), D0 ; Load local variable
        MOVE.L #1, D1 ; Load variable
        ADDM D0, D1
        MOVE.L D1, (Variable16) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 5, Operator 2 -> LiteralOp(value: 1), Store in -> 5

        MOVE.L (Variable5), D0 ; Load local variable
        MOVE.L #1, D1 ; Load variable
        ADDM D0, D1
        MOVE.L D1, (Variable5) ; Store local variable
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label1
; Instruction of type: SKIP
;, Store in -> 0

Label4:
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 17

        MOVE.L (Variable3), D0 ; Load local variable
        MOVE.L D0, (Variable17) ; Store local variable
; Instruction of type: RETURN
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 3), Store in -> 17

        MOVE.L A6, A7 ; SP = BP, return to state before PMB
        MOVE.L (A7)+, A6 ; BP = old BP
        LEA DISP, A0 ; A0 = @DISP
        MOVE.L (A7)+, 8(A0) ; DISP[prof] = old value
        MOVE.L (Variable17), D5 ; Load local variable
        RTS ; Return
; Instruction of type: CALL
;Operator 1 -> 0

main:                                                       
        MOVE.L A7, A6
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 18

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 18

        LEA        Variable18, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: READ
;, Store in -> 19

        MOVE.L #5, D0 ; Prepare read
        TRAP #15 ; Expect input
        AND.L #$00FF, D1 ; Mask upper word (we read char = 2 bytes)
        MOVE.L D1, (Variable19) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 20

        MOVE.L #48, D0 ; Load variable
        MOVE.L D0, (Variable20) ; Store local variable
; Instruction of type: SUB
;Operator 1 -> 19, Operator 2 -> LiteralOp(value: 48), Store in -> 21

        MOVE.L (Variable19), D0 ; Load local variable
        MOVE.L #48, D1 ; Load variable
        SUBM D0, D1
        MOVE.L D1, (Variable21) ; Store local variable
; Instruction of type: SUB
;Operator 1 -> 19, Operator 2 -> LiteralOp(value: 48), Store in -> 22

        MOVE.L (Variable19), D0 ; Load local variable
        MOVE.L #48, D1 ; Load variable
        SUBM D0, D1
        MOVE.L D1, (Variable22) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 23

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 23

        LEA        Variable23, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: SUB
;Operator 1 -> 19, Operator 2 -> LiteralOp(value: 48), Store in -> 24

        MOVE.L (Variable19), D0 ; Load local variable
        MOVE.L #48, D1 ; Load variable
        SUBM D0, D1
        MOVE.L D1, (Variable24) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 25

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: SIMPLEPARAM
;, Store in -> 24

        SUB.L #40, A6
        MOVE.L A6, A7
        MOVE.L (Variable24), D0 ; Load local param
        MOVE.L D0, (Variable24) 
        MOVE.L (Variable24), (Param0) ; Param
; Instruction of type: SIMPLEPARAM
;, Operator 2 -> LiteralOp(value: "koalas"), Store in -> 25

        LEA Variable25, A0 ; Load local param
        MOVE.L A0, D0 ; Load it to register 
        LEA SParam0, A1 ; Param gest
        LEA Variable25, A0 ; Param gest
.loop96: 
        MOVE.B (A0)+, (A1)+ 
        TST.B (A0) 
        BNE .loop96
        MOVE.B #0, (A1)+ 
; Instruction of type: CALL
;Operator 1 -> 0, Store in -> 26

        JSR Label0 ; GOTO Label0
        MOVE.L D5, (Variable26) ; Store local variable
        MOVE.L A6, A7
; Instruction of type: PRINTLN
;Operator 1 -> 26

        MOVE.L (Variable26), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
        MOVE.B #11, D0 ; Next line prepare
        MOVE.L #$00FF, D1 ; Request current coordinates
        TRAP #15 ; Get them in D1.L (HIGH=COL, LOW = ROW)
        AND.L #$00FF, D1 ; We want always column = 0
        ADDQ.B #1, D1 ; We increment the current row by 1
        TRAP #15 ; Set new coordinates (next line ready)
; Instruction of type: RETURN
;Operator 1 -> 0

        SIMHALT
        END globals