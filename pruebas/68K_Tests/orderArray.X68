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
Variable24        DC.B ' ',0
Variable25        DC.B 'Swapping ',0
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
Variable62        DC.B ',',0
Variable63        DS.L 1
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
;Operator 1 -> 0, Store in -> 3

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 8(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 8

        MOVE.L #1, D0 ; Load variable
        MOVE.L 7(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 6(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label0:
; Instruction of type: IFEQ
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label1
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 11

        MOVE.L #0, D0 ; Load variable
        MOVE.L 10(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 10

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 9(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        MOVE.L #1, D0 ; Load variable
        MOVE.L 48(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 12

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 44(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label2:
; Instruction of type: IFLT
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 0

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        CMP.L D1, D0
        BLT Label3
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 16

        MOVE.L #0, D0 ; Load variable
        MOVE.L 15(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label4
; Instruction of type: SKIP
;, Store in -> 0

Label3:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 16

        MOVE.L #1, D0 ; Load variable
        MOVE.L 15(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label4:
; Instruction of type: IFEQ
;Operator 1 -> 16, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L 15(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label5
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 21

        MOVE.L #1, D0 ; Load variable
        MOVE.L 80(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 22

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L 84(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: IFGT
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 0

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        CMP.L D1, D0
        BGT Label6
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 23

        MOVE.L #0, D0 ; Load variable
        MOVE.L 22(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label7
; Instruction of type: SKIP
;, Store in -> 0

Label6:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 23

        MOVE.L #1, D0 ; Load variable
        MOVE.L 22(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label7:
; Instruction of type: IFEQ
;Operator 1 -> 23, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L 22(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label8
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 24

Variable0        DC.B ' ', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 2944(A6), A0 ; A0 contains parameter @
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
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 25

Variable0        DC.B 'Swapping ', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 3072(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 28

Variable0        DC.B ' with ', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 3456(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 31

        MOVE.L #1, D0 ; Load variable
        MOVE.L 120(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 32

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L 124(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
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
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 36

        MOVE.L #1, D0 ; Load variable
        MOVE.L 140(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 37

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L 144(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 33

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 128(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 40

        MOVE.L #1, D0 ; Load variable
        MOVE.L 156(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 41

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L 160(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 48

        MOVE.L #1, D0 ; Load variable
        MOVE.L 47(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 47

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 46(A6), A0 ; A0 contains parameter @
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
;Operator 1 -> 0, Store in -> 51

        MOVE.L #1, D0 ; Load variable
        MOVE.L 200(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ADD
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 52

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L 204(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 52, Store in -> 49

        MOVE.L 204(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 192(A6), A0 ; A0 contains parameter @
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
;Operator 1 -> 0, Store in -> 53

Variable0        DC.B 'El array iniciado era: {4,5,3,1,9}', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 6656(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 54

Variable0        DC.B 'El array resultante es: ', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 6784(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 55

        MOVE.L #0, D0 ; Load variable
        MOVE.L 216(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 56

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 220(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label10:
; Instruction of type: IFLT
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 0

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        CMP.L D1, D0
        BLT Label11
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 59

        MOVE.L #0, D0 ; Load variable
        MOVE.L 58(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label12
; Instruction of type: SKIP
;, Store in -> 0

Label11:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 59

        MOVE.L #1, D0 ; Load variable
        MOVE.L 58(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label12:
; Instruction of type: IFEQ
;Operator 1 -> 59, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L 58(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label13
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 62

Variable0        DC.B ',', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 7808(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: ADD
;Operator 1 -> 56, Operator 2 -> LiteralOp(value: 1), Store in -> 63

        MOVE.L 220(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #1, D1 ; Load variable
        ADDM D0, D1
        MOVE.L 248(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 63, Store in -> 56

        MOVE.L 248(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 220(A6), A0 ; A0 contains parameter @
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