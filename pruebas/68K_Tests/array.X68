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
Variable15        DS.L 15
Variable16        DS.L 1
Variable17        DS.L 15
Variable18        DS.L 1
Variable19        DS.L 1
Variable20        DS.L 1
Variable21        DS.L 1
Variable22        DS.L 1
Variable23        DS.L 1
Variable24        DS.L 1
Variable25        DS.L 1
Variable26        DS.L 1
Variable27        DS.L 1
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
;Operator 1 -> 5, Store in -> 6

        MOVE.L 16(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 20(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L #5, D0 ; Load variable
        MOVE.L 24(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ASSINDEX
;Operator 1 -> 7, Operator 2 -> ReferenceOp(ref: 6), Store in -> 2

        LEA (Variable2), A0
        MOVE.L 20(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
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
;Operator 1 -> 9, Store in -> 10

        MOVE.L 32(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
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
;Operator 1 -> 12, Store in -> 13

        MOVE.L 44(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 48(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 2, Operator 2 -> ReferenceOp(ref: 13), Store in -> 14

        LEA (Variable2), A0
        MOVE.L 48(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 52(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ASSINDEX
;Operator 1 -> 14, Operator 2 -> ReferenceOp(ref: 10), Store in -> 0

        LEA (Variable0), A0
        MOVE.L 36(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L 52(A6), A0 ; A0 contains store address
        MOVE.L D2, A0
        MOVE.L (A0), D2 ; Get parameter
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 16

        MOVE.L #2, D0 ; Load variable
        MOVE.L 60(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 18

        MOVE.L #2, D0 ; Load variable
        MOVE.L 68(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 19

        MOVE.L #0, D0 ; Load variable
        MOVE.L 72(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 19, Operator 2 -> LiteralOp(value: 4), Store in -> 20

        MOVE.L 72(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 76(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 20, Store in -> 21

        MOVE.L 76(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 80(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 22

        MOVE.L #1, D0 ; Load variable
        MOVE.L 84(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ASSINDEX
;Operator 1 -> 22, Operator 2 -> ReferenceOp(ref: 21), Store in -> 15

        LEA (Variable15), A0
        MOVE.L 80(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L 84(A6), A0 ; A0 contains store address
        MOVE.L D2, A0
        MOVE.L (A0), D2 ; Get parameter
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 23

        MOVE.L #1, D0 ; Load variable
        MOVE.L 88(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 23, Operator 2 -> LiteralOp(value: 4), Store in -> 24

        MOVE.L 88(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 92(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 24, Store in -> 25

        MOVE.L 92(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 96(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 26

        MOVE.L #2, D0 ; Load variable
        MOVE.L 100(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ASSINDEX
;Operator 1 -> 26, Operator 2 -> ReferenceOp(ref: 25), Store in -> 15

        LEA (Variable15), A0
        MOVE.L 96(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L 100(A6), A0 ; A0 contains store address
        MOVE.L D2, A0
        MOVE.L (A0), D2 ; Get parameter
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 15, Store in -> 27

        MOVE.L 56(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 104(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: RETURN
;Operator 1 -> 0

        SIMHALT
        END globals