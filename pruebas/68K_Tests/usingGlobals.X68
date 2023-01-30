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
Variable11        DS.L 1
Variable12        DC.B 'Result is: ',0
Variable13        DS.L 1
Variable14        DS.L 1
Variable15        DS.L 1
Variable16        DS.L 1
;-----------------------------------------------------------
        DS.W 0
;-----------------------------------------------------------
globals:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 0

        MOVE.L #4, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 1

        MOVE.L -4(A6), D0 ; Load local variable
; Instruction of type: SKIP
;, Store in -> 0

        JMP main ; Declared all globals
Label0:
; Instruction of type: INIT
;, Store in -> 0

        LEA DISP, A0
        MOVE.L 8(A0), -(A7)
        MOVE.L A6, -(A7)
        MOVE.L A7, A6 ;BP = SP
        MOVE.L A6, 8(A7) ; DISP(prof) = BP
        SUB.L #VARSIZE, A7 ; SP changed to assign Variable Space
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 3

        MOVE.L #0, D0 ; Load variable
        MOVE.L 8(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 2, Store in -> 4

        MOVE.L 12(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 5

        MOVE.L #4, D0 ; Load variable
        MOVE.L 16(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 6

        MOVE.L #2, D0 ; Load variable
        MOVE.L 20(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: DIV
;Operator 1 -> 5, Operator 2 -> ReferenceOp(ref: 6), Store in -> 7

        MOVE.L 16(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 20(A6), A0 ; A0 contains store address
        MOVE.L D1, A0
        MOVE.L (A0), D1 ; Get parameter
        DIVM D1, D0
        AND.L #$0000FFFF, D1
        MOVE.L 24(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 8

        MOVE.L #3, D0 ; Load variable
        MOVE.L 28(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 7, Operator 2 -> ReferenceOp(ref: 8), Store in -> 9

        MOVE.L 24(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 28(A6), A0 ; A0 contains store address
        MOVE.L D1, A0
        MOVE.L (A0), D1 ; Get parameter
        MULTM D0, D1
        MOVE.L 32(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: ADD
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 9), Store in -> 10

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 32(A6), A0 ; A0 contains store address
        MOVE.L D1, A0
        MOVE.L (A0), D1 ; Get parameter
        ADDM D0, D1
        MOVE.L 36(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 10, Store in -> 11

        MOVE.L 36(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 40(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 12

Variable0        DC.B 'Result is: ', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 1408(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 12

        MOVE.L 1408(A6), A0 ; A0 contains store address
        MOVE.L (A0), A0 ; Get parameter
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        MOVE.L #0, D0 ; Load variable
        MOVE.L 48(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 11, Store in -> 14

        MOVE.L 40(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 52(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINTLN
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
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
;Operator 1 -> 0, Store in -> 15

        MOVE.L #0, D0 ; Load variable
        MOVE.L 56(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 1, Store in -> 16

        MOVE.L 60(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SIMPLEPARAM
;, Store in -> 0

        MOVE.L 52(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L D0, -(A7)
; Instruction of type: CALL
;Operator 1 -> 0

        JSR Label0 ; GOTO Label0
        MOVE.L A6, A7
; Instruction of type: RETURN
;Operator 1 -> 0

        SIMHALT
        END globals