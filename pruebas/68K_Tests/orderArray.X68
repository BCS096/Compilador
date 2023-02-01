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
Variable12        DS.L 1
Variable13        DS.L 1
Variable14        DS.L 1
Variable15        DS.L 1
Variable16        DS.L 1
Variable17        DS.L 1
Variable18        DS.L 1
Variable19        DS.L 1
Variable20        DS.L 1
Variable21        DS.L 1
Variable22        DS.L 1
Variable23        DS.L 1
Variable24        DS.L 1
Variable25        DC.B ' ',0
Variable26        DC.B 'Swapping ',0
Variable27        DS.L 1
Variable28        DS.L 1
Variable29        DS.L 1
Variable30        DS.L 1
Variable31        DC.B ' with ',0
Variable32        DS.L 1
Variable33        DS.L 1
Variable34        DS.L 1
Variable35        DS.L 1
Variable36        DS.L 1
Variable37        DS.L 1
Variable38        DS.L 1
Variable39        DS.L 1
Variable40        DS.L 1
Variable41        DS.L 1
Variable42        DS.L 1
Variable43        DS.L 1
Variable44        DS.L 1
Variable45        DS.L 1
Variable46        DS.L 1
Variable47        DS.L 1
Variable48        DS.L 1
Variable49        DS.L 1
Variable50        DS.L 1
Variable51        DS.L 1
Variable52        DS.L 1
Variable53        DS.L 1
Variable54        DS.L 1
Variable55        DS.L 1
Variable56        DS.L 1
Variable57        DS.L 1
Variable58        DS.L 1
Variable59        DS.L 1
Variable60        DS.L 1
Variable61        DC.B 'El array iniciado era: {4,5,3,1,9}',0
Variable62        DC.B 'El array resultante es: ',0
Variable63        DS.L 1
Variable64        DS.L 1
Variable65        DS.L 1
Variable66        DS.L 1
Variable67        DS.L 1
Variable68        DS.L 1
Variable69        DS.L 1
Variable70        DS.L 1
Variable71        DS.L 1
Variable72        DC.B ',',0
Variable73        DS.L 1
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

        MOVE.L #2, D0 ; Load variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 2

        MOVE.L #5, D0 ; Load variable
        MOVE.L 4(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 2, Store in -> 3

        MOVE.L 4(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 8(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L #1, D0 ; Load variable
        MOVE.L 6(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 7, Store in -> 6

        MOVE.L 6(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 5(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label0:
; Instruction of type: CLONE
;Operator 1 -> 6, Store in -> 8

        MOVE.L 5(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 7(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: IFEQ
;Operator 1 -> 8, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L 7(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label1
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 9

        MOVE.L #0, D0 ; Load variable
        MOVE.L 8(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 9, Store in -> 6

        MOVE.L 8(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 5(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 10

        MOVE.L #1, D0 ; Load variable
        MOVE.L 36(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 10, Store in -> 4

        MOVE.L 36(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 12(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label2:
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 11

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 40(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 12

        MOVE.L 8(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 44(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: IFLT
;Operator 1 -> 11, Operator 2 -> ReferenceOp(ref: 12), Store in -> 0

        MOVE.L 40(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 44(A6), A0 ; A0 contains store address
        MOVE.L (A0), D1 ; Get parameter
        CMP.L D1, D0
        BLT Label3
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        MOVE.L #0, D0 ; Load variable
        MOVE.L 12(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label4
; Instruction of type: SKIP
;, Store in -> 0

Label3:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        MOVE.L #1, D0 ; Load variable
        MOVE.L 12(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label4:
; Instruction of type: IFEQ
;Operator 1 -> 13, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label5
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 14

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 52(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 14, Operator 2 -> LiteralOp(value: 4), Store in -> 15

        MOVE.L 52(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 56(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 15, Store in -> 16

        MOVE.L 56(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 60(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 16), Store in -> 17

        LEA (Variable0), A0
        MOVE.L 60(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 64(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 18

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 68(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 19

        MOVE.L #1, D0 ; Load variable
        MOVE.L 72(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 18, Operator 2 -> ReferenceOp(ref: 19), Store in -> 20

        MOVE.L 68(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 72(A6), A0 ; A0 contains store address
        MOVE.L (A0), D1 ; Get parameter
        SUBM D0, D1
        MOVE.L 76(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 20, Operator 2 -> LiteralOp(value: 4), Store in -> 21

        MOVE.L 76(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 80(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 21, Store in -> 22

        MOVE.L 80(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 84(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 22), Store in -> 23

        LEA (Variable0), A0
        MOVE.L 84(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 88(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: IFGT
;Operator 1 -> 17, Operator 2 -> ReferenceOp(ref: 23), Store in -> 0

        MOVE.L 64(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 88(A6), A0 ; A0 contains store address
        MOVE.L (A0), D1 ; Get parameter
        CMP.L D1, D0
        BGT Label6
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 24

        MOVE.L #0, D0 ; Load variable
        MOVE.L 23(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label7
; Instruction of type: SKIP
;, Store in -> 0

Label6:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 24

        MOVE.L #1, D0 ; Load variable
        MOVE.L 23(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label7:
; Instruction of type: IFEQ
;Operator 1 -> 24, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L 23(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label8
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 25

.Variable0        DC.B ' ', 0 ; Inmediate string
        LEA .Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 3072(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINTLN
;Operator 1 -> 25

        MOVE.L 3072(A6), A0 ; A0 contains store address
        MOVE.L (A0), A0 ; Get parameter
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
;Operator 1 -> 0, Store in -> 26

        LEA .Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 3200(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 26

        MOVE.L 3200(A6), A0 ; A0 contains store address
        MOVE.L (A0), A0 ; Get parameter
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 27

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 104(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 27, Operator 2 -> LiteralOp(value: 4), Store in -> 28

        MOVE.L 104(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 108(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 28, Store in -> 29

        MOVE.L 108(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 112(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 29), Store in -> 30

        LEA (Variable0), A0
        MOVE.L 112(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 116(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 30

        MOVE.L 116(A6), A0 ; A0 contains store address
        MOVE.L (A0), D1 ; Get parameter
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 31

        LEA .Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 3840(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 31

        MOVE.L 3840(A6), A0 ; A0 contains store address
        MOVE.L (A0), A0 ; Get parameter
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 32

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 124(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 33

        MOVE.L #1, D0 ; Load variable
        MOVE.L 128(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 32, Operator 2 -> ReferenceOp(ref: 33), Store in -> 34

        MOVE.L 124(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 128(A6), A0 ; A0 contains store address
        MOVE.L (A0), D1 ; Get parameter
        SUBM D0, D1
        MOVE.L 132(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 34, Operator 2 -> LiteralOp(value: 4), Store in -> 35

        MOVE.L 132(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 136(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 35, Store in -> 36

        MOVE.L 136(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 140(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 36), Store in -> 37

        LEA (Variable0), A0
        MOVE.L 140(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 144(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINTLN
;Operator 1 -> 37

        MOVE.L 144(A6), A0 ; A0 contains store address
        MOVE.L (A0), D1 ; Get parameter
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
        MOVE.B #11, D0 ; Next line prepare
        MOVE.L #$00FF, D1 ; Request current coordinates
        TRAP #15 ; Get them in D1.L (HIGH=COL, LOW = ROW)
        AND.L #$00FF, D1 ; We want always column = 0
        ADDQ.B #1, D1 ; We increment the current row by 1
        TRAP #15 ; Set new coordinates (next line ready)
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 38

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 148(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 39

        MOVE.L #1, D0 ; Load variable
        MOVE.L 152(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 38, Operator 2 -> ReferenceOp(ref: 39), Store in -> 40

        MOVE.L 148(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 152(A6), A0 ; A0 contains store address
        MOVE.L (A0), D1 ; Get parameter
        SUBM D0, D1
        MOVE.L 156(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 40, Operator 2 -> LiteralOp(value: 4), Store in -> 41

        MOVE.L 156(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 160(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 41, Store in -> 42

        MOVE.L 160(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 164(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 42), Store in -> 43

        LEA (Variable0), A0
        MOVE.L 164(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 168(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 43, Store in -> 5

        MOVE.L 168(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 16(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 44

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 172(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 45

        MOVE.L #1, D0 ; Load variable
        MOVE.L 176(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 44, Operator 2 -> ReferenceOp(ref: 45), Store in -> 46

        MOVE.L 172(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 176(A6), A0 ; A0 contains store address
        MOVE.L (A0), D1 ; Get parameter
        SUBM D0, D1
        MOVE.L 180(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 46, Operator 2 -> LiteralOp(value: 4), Store in -> 47

        MOVE.L 180(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 184(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 47, Store in -> 48

        MOVE.L 184(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 188(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 49

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 192(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 49, Operator 2 -> LiteralOp(value: 4), Store in -> 50

        MOVE.L 192(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 196(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 50, Store in -> 51

        MOVE.L 196(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 200(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 51), Store in -> 52

        LEA (Variable0), A0
        MOVE.L 200(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 204(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ASSINDEX
;Operator 1 -> 52, Operator 2 -> ReferenceOp(ref: 48), Store in -> 0

        LEA (Variable0), A0
        MOVE.L 188(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L 204(A6), A0 ; A0 contains store address
        MOVE.L (A0), D2 ; Get parameter
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 53

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 208(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 53, Operator 2 -> LiteralOp(value: 4), Store in -> 54

        MOVE.L 208(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 212(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 54, Store in -> 55

        MOVE.L 212(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 216(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 5, Store in -> 56

        MOVE.L 16(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 220(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ASSINDEX
;Operator 1 -> 56, Operator 2 -> ReferenceOp(ref: 55), Store in -> 0

        LEA (Variable0), A0
        MOVE.L 216(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L 220(A6), A0 ; A0 contains store address
        MOVE.L (A0), D2 ; Get parameter
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 57

        MOVE.L #1, D0 ; Load variable
        MOVE.L 56(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 57, Store in -> 6

        MOVE.L 56(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 5(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label9
; Instruction of type: SKIP
;, Store in -> 0

Label8:
; Instruction of type: SKIP
;, Store in -> 0

Label9:
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 58

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 228(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 59

        MOVE.L #1, D0 ; Load variable
        MOVE.L 232(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ADD
;Operator 1 -> 58, Operator 2 -> ReferenceOp(ref: 59), Store in -> 60

        MOVE.L 228(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 232(A6), A0 ; A0 contains store address
        MOVE.L (A0), D1 ; Get parameter
        ADDM D0, D1
        MOVE.L 236(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 60, Store in -> 4

        MOVE.L 236(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 12(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label2
; Instruction of type: SKIP
;, Store in -> 0

Label5:
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label0
; Instruction of type: SKIP
;, Store in -> 0

Label1:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 61

        LEA .Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 7680(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 61

        MOVE.L 7680(A6), A0 ; A0 contains store address
        MOVE.L (A0), A0 ; Get parameter
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 62

        LEA .Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 7808(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 62

        MOVE.L 7808(A6), A0 ; A0 contains store address
        MOVE.L (A0), A0 ; Get parameter
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 63

        MOVE.L #0, D0 ; Load variable
        MOVE.L 248(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 63, Store in -> 64

        MOVE.L 248(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 252(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label10:
; Instruction of type: CLONE
;Operator 1 -> 64, Store in -> 65

        MOVE.L 252(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 256(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 66

        MOVE.L 8(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 260(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: IFLT
;Operator 1 -> 65, Operator 2 -> ReferenceOp(ref: 66), Store in -> 0

        MOVE.L 256(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 260(A6), A0 ; A0 contains store address
        MOVE.L (A0), D1 ; Get parameter
        CMP.L D1, D0
        BLT Label11
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 67

        MOVE.L #0, D0 ; Load variable
        MOVE.L 66(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label12
; Instruction of type: SKIP
;, Store in -> 0

Label11:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 67

        MOVE.L #1, D0 ; Load variable
        MOVE.L 66(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label12:
; Instruction of type: IFEQ
;Operator 1 -> 67, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L 66(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label13
; Instruction of type: CLONE
;Operator 1 -> 64, Store in -> 68

        MOVE.L 252(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 268(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 68, Operator 2 -> LiteralOp(value: 4), Store in -> 69

        MOVE.L 268(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 272(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 69, Store in -> 70

        MOVE.L 272(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 276(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 70), Store in -> 71

        LEA (Variable0), A0
        MOVE.L 276(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 280(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 71

        MOVE.L 280(A6), A0 ; A0 contains store address
        MOVE.L (A0), D1 ; Get parameter
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 72

        LEA .Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 9088(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 72

        MOVE.L 9088(A6), A0 ; A0 contains store address
        MOVE.L (A0), A0 ; Get parameter
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: ADD
;Operator 1 -> 64, Operator 2 -> LiteralOp(value: 1), Store in -> 73

        MOVE.L 252(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #1, D1 ; Load variable
        ADDM D0, D1
        MOVE.L 288(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 64, Store in -> 73

        MOVE.L 252(A6), A0 ; A0 contains store address
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 288(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label10
; Instruction of type: SKIP
;, Store in -> 0

Label13:
; Instruction of type: RETURN
;Operator 1 -> 0

        SIMHALT
        END globals