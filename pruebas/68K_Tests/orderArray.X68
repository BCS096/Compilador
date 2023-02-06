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
Variable23        DC.B ' ',0
Variable24        DC.B 'Swapping ',0
Variable25        DS.L 1
Variable26        DS.L 1
Variable27        DS.L 1
Variable28        DC.B ' with ',0
Variable29        DS.L 1
Variable30        DS.L 1
Variable31        DS.L 1
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
Variable53        DC.B 'El array iniciado era: {4,5,3,1,9}',0
Variable54        DC.B 'El array resultante es: ',0
Variable55        DS.L 1
Variable56        DS.L 1
Variable57        DS.L 1
Variable58        DS.L 1
Variable59        DS.L 1
Variable60        DS.L 1
Variable61        DS.L 1
Variable62        DS.L 1
Variable63        DC.B ',',0
Variable64        DS.L 1
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
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 1), Store in -> 1

        MOVE.L #4, D0 ; Load variable
        MOVE.L -8(A6), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 2

        MOVE.L #5, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 2, Store in -> 3

        MOVE.L -12(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 7, Store in -> 6

        MOVE.L -32(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label0:
; Instruction of type: CLONE
;Operator 1 -> 6, Store in -> 8

        MOVE.L -28(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: IFEQ
;Operator 1 -> 8, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L -36(A6), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label1
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 9

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 9, Store in -> 6

        MOVE.L -40(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 10

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 10, Store in -> 4

        MOVE.L -44(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label2:
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 11

        MOVE.L -20(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 12

        MOVE.L -16(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: IFLT
;Operator 1 -> 11, Operator 2 -> ReferenceOp(ref: 12), Store in -> 0

        MOVE.L -48(A6), D0 ; Load local variable
        MOVE.L -52(A6), D1 ; Load local variable
        CMP.L D1, D0
        BLT Label3
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label4
; Instruction of type: SKIP
;, Store in -> 0

Label3:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label4:
; Instruction of type: IFEQ
;Operator 1 -> 13, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L -56(A6), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label5
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 14

        MOVE.L -20(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 14, Operator 2 -> LiteralOp(value: 4), Store in -> 15

        MOVE.L -60(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 15), Store in -> 16

        LEA (Variable0), A0
        MOVE.L -64(A6), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 17

        MOVE.L -20(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 18

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: SUB
;Operator 1 -> 17, Operator 2 -> ReferenceOp(ref: 18), Store in -> 19

        MOVE.L -72(A6), D0 ; Load local variable
        MOVE.L -76(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 19, Operator 2 -> LiteralOp(value: 4), Store in -> 20

        MOVE.L -80(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 20), Store in -> 21

        LEA (Variable0), A0
        MOVE.L -84(A6), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: IFGT
;Operator 1 -> 16, Operator 2 -> ReferenceOp(ref: 21), Store in -> 0

        MOVE.L -68(A6), D0 ; Load local variable
        MOVE.L -88(A6), D1 ; Load local variable
        CMP.L D1, D0
        BGT Label6
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 22

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label7
; Instruction of type: SKIP
;, Store in -> 0

Label6:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 22

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label7:
; Instruction of type: IFEQ
;Operator 1 -> 22, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L -92(A6), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label8
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 23

; Instruction of type: PRINTLN
;Operator 1 -> 23

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
;Operator 1 -> 0, Store in -> 24

; Instruction of type: PRINT
;Operator 1 -> 24

        LEA        Variable24, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 25

        MOVE.L -20(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 25, Operator 2 -> LiteralOp(value: 4), Store in -> 26

        MOVE.L -96(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 26), Store in -> 27

        LEA (Variable0), A0
        MOVE.L -100(A6), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: PRINT
;Operator 1 -> 27

        MOVE.L -104(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 28

; Instruction of type: PRINT
;Operator 1 -> 28

        LEA        Variable28, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 29

        MOVE.L -20(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 30

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: SUB
;Operator 1 -> 29, Operator 2 -> ReferenceOp(ref: 30), Store in -> 31

        MOVE.L -108(A6), D0 ; Load local variable
        MOVE.L -112(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 31, Operator 2 -> LiteralOp(value: 4), Store in -> 32

        MOVE.L -116(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 32), Store in -> 33

        LEA (Variable0), A0
        MOVE.L -120(A6), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: PRINTLN
;Operator 1 -> 33

        MOVE.L -124(A6), D1 ; Load local variable
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
;Operator 1 -> 4, Store in -> 34

        MOVE.L -20(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 35

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: SUB
;Operator 1 -> 34, Operator 2 -> ReferenceOp(ref: 35), Store in -> 36

        MOVE.L -128(A6), D0 ; Load local variable
        MOVE.L -132(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 36, Operator 2 -> LiteralOp(value: 4), Store in -> 37

        MOVE.L -136(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 37), Store in -> 38

        LEA (Variable0), A0
        MOVE.L -140(A6), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 38, Store in -> 5

        MOVE.L -144(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 39

        MOVE.L -20(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 40

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: SUB
;Operator 1 -> 39, Operator 2 -> ReferenceOp(ref: 40), Store in -> 41

        MOVE.L -148(A6), D0 ; Load local variable
        MOVE.L -152(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 41, Operator 2 -> LiteralOp(value: 4), Store in -> 42

        MOVE.L -156(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 43

        MOVE.L -20(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 43, Operator 2 -> LiteralOp(value: 4), Store in -> 44

        MOVE.L -164(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 44), Store in -> 45

        LEA (Variable0), A0
        MOVE.L -168(A6), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 45, Operator 2 -> ReferenceOp(ref: 42), Store in -> 0

        LEA (Variable0), A0
        MOVE.L -160(A6), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L -172(A6), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 46

        MOVE.L -20(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 46, Operator 2 -> LiteralOp(value: 4), Store in -> 47

        MOVE.L -176(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 5, Store in -> 48

        MOVE.L -24(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 48, Operator 2 -> ReferenceOp(ref: 47), Store in -> 0

        LEA (Variable0), A0
        MOVE.L -180(A6), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L -184(A6), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 49

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 49, Store in -> 6

        MOVE.L -188(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
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
;Operator 1 -> 4, Store in -> 50

        MOVE.L -20(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 51

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 50, Operator 2 -> ReferenceOp(ref: 51), Store in -> 52

        MOVE.L -192(A6), D0 ; Load local variable
        MOVE.L -196(A6), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 52, Store in -> 4

        MOVE.L -200(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
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
;Operator 1 -> 0, Store in -> 53

; Instruction of type: PRINT
;Operator 1 -> 53

        LEA        Variable53, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 54

; Instruction of type: PRINT
;Operator 1 -> 54

        LEA        Variable54, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 55

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 55, Store in -> 56

        MOVE.L -204(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label10:
; Instruction of type: CLONE
;Operator 1 -> 56, Store in -> 57

        MOVE.L -208(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 58

        MOVE.L -16(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: IFLT
;Operator 1 -> 57, Operator 2 -> ReferenceOp(ref: 58), Store in -> 0

        MOVE.L -212(A6), D0 ; Load local variable
        MOVE.L -216(A6), D1 ; Load local variable
        CMP.L D1, D0
        BLT Label11
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 59

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label12
; Instruction of type: SKIP
;, Store in -> 0

Label11:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 59

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label12:
; Instruction of type: IFEQ
;Operator 1 -> 59, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L -220(A6), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label13
; Instruction of type: CLONE
;Operator 1 -> 56, Store in -> 60

        MOVE.L -208(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 60, Operator 2 -> LiteralOp(value: 4), Store in -> 61

        MOVE.L -224(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 61), Store in -> 62

        LEA (Variable0), A0
        MOVE.L -228(A6), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, -(A7) ; Store local variable
; Instruction of type: PRINT
;Operator 1 -> 62

        MOVE.L -232(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 63

; Instruction of type: PRINT
;Operator 1 -> 63

        LEA        Variable63, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: ADD
;Operator 1 -> 56, Operator 2 -> LiteralOp(value: 1), Store in -> 64

        MOVE.L -208(A6), D0 ; Load local variable
        MOVE.L #1, D1 ; Load variable
        ADDM D0, D1
        MOVE.L D1, -(A7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 64, Store in -> 56

        MOVE.L -236(A6), D0 ; Load local variable
        MOVE.L D0, -(A7) ; Store local variable
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