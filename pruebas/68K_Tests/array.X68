        INCLUDE  library/MACRO.X68
        INCLUDE  library/CONST.X68
        INCLUDE  library/VAR.X68
;-----------------------------------------------------------
;-----------------------------------------------------------
; Initial program lines, main                               
;-----------------------------------------------------------
; Labels to memory space reserved for variables:            
;-----------------------------------------------------------
Variable0        DS.L 15
Variable1        DS.L 1
Variable2        DS.L 15
Variable3        DS.L 1
Variable4        DS.L 1
Variable5        DS.L 1
Variable6        DS.L 1
Variable7        DS.L 1
Variable8        DS.L 1
Variable9        DS.L 1
Variable10        DS.L 1
Variable11        DS.L 1
Variable12        DS.L 1
Variable13        DS.L 1
Variable14        DS.L 1
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
;Operator 1 -> 0, Store in -> 1

        MOVE.L #5, D0 ; Load variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 3

        MOVE.L #1, D0 ; Load variable
        MOVE.L 8(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 4

        MOVE.L #0, D0 ; Load variable
        MOVE.L 12(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 4, Operator 2 -> LiteralOp(value: 4), Store in -> 5

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 16(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 6

        MOVE.L #5, D0 ; Load variable
        MOVE.L 20(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L #5, D0 ; Load variable
        MOVE.L 24(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ASSINDEX
;Operator 1 -> 7, Operator 2 -> LiteralOp(value: 6), Store in -> 2

        LEA (Variable2), A0
        MOVE.L #6, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L 24(A6), A0 ; A0 contains store address
        MOVE.L D2, A0
        MOVE.L (A0), D2 ; Get parameter
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 8

        MOVE.L #0, D0 ; Load variable
        MOVE.L 28(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 8, Operator 2 -> LiteralOp(value: 4), Store in -> 9

        MOVE.L 28(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 32(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 10

        MOVE.L #9, D0 ; Load variable
        MOVE.L 36(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 11

        MOVE.L #0, D0 ; Load variable
        MOVE.L 40(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 11, Operator 2 -> LiteralOp(value: 4), Store in -> 12

        MOVE.L 40(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 44(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        MOVE.L #12, D0 ; Load variable
        MOVE.L 48(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 2, Operator 2 -> LiteralOp(value: 13), Store in -> 14

        LEA (Variable2), A0
        MOVE.L #13, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 52(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ASSINDEX
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 10), Store in -> 0

        LEA (Variable0), A0
        MOVE.L #10, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L -4(A6), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: RETURN
;Operator 1 -> 0

        SIMHALT
        END globals