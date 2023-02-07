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
Variable1        DS.L 1
Variable2        DS.L 1
Variable3        DS.L 1
Variable4        DS.L 1
Variable5        DS.L 1
Variable6        DS.L 1
Variable7        DS.L 1
Variable8        DS.L 1
Variable9        DS.L 1
Variable10        DS.L 1
Variable11        DC.B 'Result is: ',0
Variable12        DS.L 1
Variable13        DS.L 1
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
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 0

        MOVE.L #4, D0 ; Load variable
        MOVE.L D0, (Variable0) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 1

        MOVE.L (Variable0), D0 ; Load local variable
        MOVE.L D0, (Variable1) ; Store local variable
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
        MOVE.L (Param0), (Variable2) ; Param gest
; Instruction of type: CLONE
;Operator 1 -> 2, Store in -> 3

        MOVE.L (Variable2), D0 ; Load local param
        MOVE.L D0, (Variable3) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 4

        MOVE.L #4, D0 ; Load variable
        MOVE.L D0, (Variable4) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 5

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable5) ; Store local variable
; Instruction of type: DIV
;Operator 1 -> 4, Operator 2 -> ReferenceOp(ref: 5), Store in -> 6

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L (Variable5), D1 ; Load local variable
        DIVM D1, D0
        AND.L #$0000FFFF, D1
        MOVE.L D1, (Variable6) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L #3, D0 ; Load variable
        MOVE.L D0, (Variable7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 6, Operator 2 -> ReferenceOp(ref: 7), Store in -> 8

        MOVE.L (Variable6), D0 ; Load local variable
        MOVE.L (Variable7), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable8) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 3, Operator 2 -> ReferenceOp(ref: 8), Store in -> 9

        MOVE.L (Variable3), D0 ; Load local variable
        MOVE.L (Variable8), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, (Variable9) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 9, Store in -> 10

        MOVE.L (Variable9), D0 ; Load local variable
        MOVE.L D0, (Variable10) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 11

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 11

        LEA        Variable11, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 10, Store in -> 12

        MOVE.L (Variable10), D0 ; Load local variable
        MOVE.L D0, (Variable12) ; Store local variable
; Instruction of type: PRINTLN
;Operator 1 -> 12

        MOVE.L (Variable12), D1 ; Load local variable
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

        MOVE.L A6, A7 ; SP = BP, return to state before PMB
        MOVE.L (A7)+, A6 ; BP = old BP
        LEA DISP, A0 ; A0 = @DISP
        MOVE.L (A7)+, 8(A0) ; DISP[prof] = old value
        RTS ; Return
; Instruction of type: CALL
;Operator 1 -> 0

main:                                                       
        MOVE.L A7, A6
; Instruction of type: CLONE
;Operator 1 -> 1, Store in -> 13

        MOVE.L (Variable1), D0 ; Load local variable
        MOVE.L D0, (Variable13) ; Store local variable
; Instruction of type: SIMPLEPARAM
;, Store in -> 13

        SUB.L #40, A6
        MOVE.L A6, A7
        MOVE.L (Variable13), D0 ; Load local param
        MOVE.L D0, (Variable13) 
        MOVE.L (Variable13), (Param0) ; Param
; Instruction of type: CALL
;Operator 1 -> 0

        JSR Label0 ; GOTO Label0
        MOVE.L A6, A7
; Instruction of type: RETURN
;Operator 1 -> 0

        SIMHALT
        END globals