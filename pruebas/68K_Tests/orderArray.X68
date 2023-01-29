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
Variable25        DS.L 1
Variable26        DS.L 1
Variable27        DS.L 1
Variable28        DS.L 1
Variable29        DS.L 1
Variable30        DS.L 1
Variable31        DS.L 1
Variable32        DS.L 1
Variable33        DC.B ' ',0
Variable34        DC.B 'Swapping ',0
Variable35        DS.L 1
Variable36        DS.L 1
Variable37        DS.L 1
Variable38        DS.L 1
Variable39        DS.L 1
Variable40        DC.B ' with ',0
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
Variable61        DS.L 1
Variable62        DS.L 1
Variable63        DS.L 1
Variable64        DS.L 1
Variable65        DS.L 1
Variable66        DS.L 1
Variable67        DS.L 1
Variable68        DS.L 1
Variable69        DS.L 1
Variable70        DS.L 1
Variable71        DS.L 1
Variable72        DS.L 1
Variable73        DS.L 1
Variable74        DS.L 1
Variable75        DS.L 1
Variable76        DS.L 1
Variable77        DS.L 1
Variable78        DS.L 1
Variable79        DS.L 1
Variable80        DC.B 'El array iniciado era: {4,5,3,1,9}',0
Variable81        DC.B 'El array resultante es: ',0
Variable82        DS.L 1
Variable83        DS.L 1
Variable84        DS.L 1
Variable85        DS.L 1
Variable86        DS.L 1
Variable87        DS.L 1
Variable88        DS.L 1
Variable89        DS.L 1
Variable90        DS.L 1
Variable91        DS.L 1
Variable92        DS.L 1
Variable93        DS.L 1
Variable94        DC.B ',',0
Variable95        DS.L 1
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
;Operator 1 -> 0, Store in -> 7

        MOVE.L #0, D0 ; Load variable
        MOVE.L 24(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 8

        MOVE.L #1, D0 ; Load variable
        MOVE.L 7(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 6

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 5(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label0:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 9

        MOVE.L #0, D0 ; Load variable
        MOVE.L 32(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 6, Store in -> 10

        MOVE.L 5(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 36(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: IFEQ
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label1
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 11

        MOVE.L #0, D0 ; Load variable
        MOVE.L 40(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 12

        MOVE.L #0, D0 ; Load variable
        MOVE.L 11(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 6

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 5(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        MOVE.L #0, D0 ; Load variable
        MOVE.L 48(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 14

        MOVE.L #1, D0 ; Load variable
        MOVE.L 52(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 4

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 12(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label2:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 15

        MOVE.L #0, D0 ; Load variable
        MOVE.L 56(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 16

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 60(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 17

        MOVE.L #0, D0 ; Load variable
        MOVE.L 64(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 18

        MOVE.L 8(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 68(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: IFLT
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 0

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        CMP.L D1, D0
        BLT Label3
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 19

        MOVE.L #0, D0 ; Load variable
        MOVE.L 18(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label4
; Instruction of type: SKIP
;, Store in -> 0

Label3:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 19

        MOVE.L #1, D0 ; Load variable
        MOVE.L 18(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label4:
; Instruction of type: IFEQ
;Operator 1 -> 19, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L 18(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label5
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 20

        MOVE.L #0, D0 ; Load variable
        MOVE.L 76(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 21

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 80(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 4), Store in -> 22

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 84(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 23

        MOVE.L #22, D0 ; Load variable
        MOVE.L 88(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 23), Store in -> 24

        LEA (Variable0), A0
        MOVE.L #23, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 92(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 25

        MOVE.L #0, D0 ; Load variable
        MOVE.L 96(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 26

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 100(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 27

        MOVE.L #1, D0 ; Load variable
        MOVE.L 104(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 28

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L 108(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 28, Operator 2 -> LiteralOp(value: 4), Store in -> 29

        MOVE.L 108(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 112(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 30

        MOVE.L #29, D0 ; Load variable
        MOVE.L 116(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 30), Store in -> 31

        LEA (Variable0), A0
        MOVE.L #30, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 120(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: IFGT
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 0

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        CMP.L D1, D0
        BGT Label6
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 32

        MOVE.L #0, D0 ; Load variable
        MOVE.L 31(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label7
; Instruction of type: SKIP
;, Store in -> 0

Label6:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 32

        MOVE.L #1, D0 ; Load variable
        MOVE.L 31(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label7:
; Instruction of type: IFEQ
;Operator 1 -> 32, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L 31(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label8
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 33

Variable0        DC.B ' ', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 4096(A6), A0 ; A0 contains parameter @
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
;Operator 1 -> 0, Store in -> 34

Variable0        DC.B 'Swapping ', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 4224(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 35

        MOVE.L #0, D0 ; Load variable
        MOVE.L 136(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 36

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 140(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 4), Store in -> 37

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 144(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 38

        MOVE.L #37, D0 ; Load variable
        MOVE.L 148(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 38), Store in -> 39

        LEA (Variable0), A0
        MOVE.L #38, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 152(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 40

Variable0        DC.B ' with ', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 4992(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 41

        MOVE.L #0, D0 ; Load variable
        MOVE.L 160(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 42

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 164(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 43

        MOVE.L #1, D0 ; Load variable
        MOVE.L 168(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 44

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L 172(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 44, Operator 2 -> LiteralOp(value: 4), Store in -> 45

        MOVE.L 172(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 176(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 46

        MOVE.L #45, D0 ; Load variable
        MOVE.L 180(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 46), Store in -> 47

        LEA (Variable0), A0
        MOVE.L #46, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 184(A6), A0 ; A0 contains parameter @
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
;Operator 1 -> 0, Store in -> 48

        MOVE.L #0, D0 ; Load variable
        MOVE.L 188(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 49

        MOVE.L #0, D0 ; Load variable
        MOVE.L 192(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 50

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 196(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 51

        MOVE.L #1, D0 ; Load variable
        MOVE.L 200(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 52

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L 204(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 52, Operator 2 -> LiteralOp(value: 4), Store in -> 53

        MOVE.L 204(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 208(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 54

        MOVE.L #53, D0 ; Load variable
        MOVE.L 212(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 54), Store in -> 55

        LEA (Variable0), A0
        MOVE.L #54, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 216(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 5

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 16(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 56

        MOVE.L #0, D0 ; Load variable
        MOVE.L 220(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 57

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 224(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 58

        MOVE.L #1, D0 ; Load variable
        MOVE.L 228(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SUB
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 59

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L 232(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 59, Operator 2 -> LiteralOp(value: 4), Store in -> 60

        MOVE.L 232(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 236(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 61

        MOVE.L #60, D0 ; Load variable
        MOVE.L 240(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 62

        MOVE.L #0, D0 ; Load variable
        MOVE.L 244(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 63

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 248(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 4), Store in -> 64

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 252(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 65

        MOVE.L #64, D0 ; Load variable
        MOVE.L 256(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 65), Store in -> 66

        LEA (Variable0), A0
        MOVE.L #65, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 260(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ASSINDEX
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 61), Store in -> 0

        LEA (Variable0), A0
        MOVE.L #61, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L -4(A6), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 67

        MOVE.L #0, D0 ; Load variable
        MOVE.L 264(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 68

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 268(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 4), Store in -> 69

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 272(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 70

        MOVE.L #69, D0 ; Load variable
        MOVE.L 276(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 71

        MOVE.L #0, D0 ; Load variable
        MOVE.L 280(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 5, Store in -> 72

        MOVE.L 16(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 284(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ASSINDEX
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 70), Store in -> 0

        LEA (Variable0), A0
        MOVE.L #70, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L -4(A6), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 73

        MOVE.L #0, D0 ; Load variable
        MOVE.L 288(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 74

        MOVE.L #1, D0 ; Load variable
        MOVE.L 73(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 6

        MOVE.L -4(A6), D0 ; Load local variable
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
;Operator 1 -> 0, Store in -> 75

        MOVE.L #0, D0 ; Load variable
        MOVE.L 296(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 76

        MOVE.L #0, D0 ; Load variable
        MOVE.L 300(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 77

        MOVE.L 12(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 304(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 78

        MOVE.L #1, D0 ; Load variable
        MOVE.L 308(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: ADD
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 79

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L 312(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 79, Store in -> 4

        MOVE.L 312(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
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
;Operator 1 -> 0, Store in -> 80

Variable0        DC.B 'El array iniciado era: {4,5,3,1,9}', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 10112(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 81

Variable0        DC.B 'El array resultante es: ', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 10240(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 82

        MOVE.L #0, D0 ; Load variable
        MOVE.L 324(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 83

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L 328(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label10:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 84

        MOVE.L #0, D0 ; Load variable
        MOVE.L 332(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 83, Store in -> 85

        MOVE.L 328(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 336(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 86

        MOVE.L #0, D0 ; Load variable
        MOVE.L 340(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 87

        MOVE.L 8(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 344(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: IFLT
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 0), Store in -> 0

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L -4(A6), D1 ; Load local variable
        CMP.L D1, D0
        BLT Label11
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 88

        MOVE.L #0, D0 ; Load variable
        MOVE.L 87(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label12
; Instruction of type: SKIP
;, Store in -> 0

Label11:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 88

        MOVE.L #1, D0 ; Load variable
        MOVE.L 87(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: SKIP
;, Store in -> 0

Label12:
; Instruction of type: IFEQ
;Operator 1 -> 88, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L 87(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BEQ Label13
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 89

        MOVE.L #0, D0 ; Load variable
        MOVE.L 352(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 83, Store in -> 90

        MOVE.L 328(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 356(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 4), Store in -> 91

        MOVE.L -4(A6), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L 360(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 92

        MOVE.L #91, D0 ; Load variable
        MOVE.L 364(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 92), Store in -> 93

        LEA (Variable0), A0
        MOVE.L #92, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L 368(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 94

Variable0        DC.B ',', 0 ; Inmediate string
        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
        MOVE.L 11904(A6), A0 ; A0 contains parameter @
        MOVE.L D0, (A0) ; Store local parameter
; Instruction of type: PRINT
;Operator 1 -> 0

        MOVE.L -4(A6), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: ADD
;Operator 1 -> 83, Operator 2 -> LiteralOp(value: 1), Store in -> 95

        MOVE.L 328(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L #1, D1 ; Load variable
        ADDM D0, D1
        MOVE.L 376(A6), A0 ; A0 contains parameter @
        MOVE.L D1, (A0) ; Store local parameter
; Instruction of type: CLONE
;Operator 1 -> 83, Store in -> 95

        MOVE.L 328(A6), A0 ; A0 contains store address
        MOVE.L D0, A0
        MOVE.L (A0), D0 ; Get parameter
        MOVE.L 376(A6), A0 ; A0 contains parameter @
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