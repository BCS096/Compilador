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
;Operator 1 -> 0, Store in -> 1

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable1) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 1), Store in -> 1

        MOVE.L #4, D0 ; Load variable
        MOVE.L (Variable1), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable1) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 2

        MOVE.L #5, D0 ; Load variable
        MOVE.L D0, (Variable2) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 2, Store in -> 3

        MOVE.L (Variable2), D0 ; Load local variable
        MOVE.L D0, (Variable3) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 7, Store in -> 6

        MOVE.L (Variable7), D0 ; Load local variable
        MOVE.L D0, (Variable6) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label0:
; Instruction of type: CLONE
;Operator 1 -> 6, Store in -> 8

        MOVE.L (Variable6), D0 ; Load local variable
        MOVE.L D0, (Variable8) ; Store local variable
; Instruction of type: IFEQ
;Operator 1 -> 8, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L (Variable8), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BNE Label1
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 9

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable9) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 9, Store in -> 6

        MOVE.L (Variable9), D0 ; Load local variable
        MOVE.L D0, (Variable6) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 10

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable10) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 10, Store in -> 4

        MOVE.L (Variable10), D0 ; Load local variable
        MOVE.L D0, (Variable4) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label2:
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 11

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L D0, (Variable11) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 12

        MOVE.L (Variable3), D0 ; Load local variable
        MOVE.L D0, (Variable12) ; Store local variable
; Instruction of type: IFLT
;Operator 1 -> 11, Operator 2 -> ReferenceOp(ref: 12), Store in -> 0

        MOVE.L (Variable11), D0 ; Load local variable
        MOVE.L (Variable12), D1 ; Load local variable
        CMP.L D1, D0
        BGE Label3
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable13) ; Store local variable
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label4
; Instruction of type: SKIP
;, Store in -> 0

Label3:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable13) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label4:
; Instruction of type: IFEQ
;Operator 1 -> 13, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L (Variable13), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BNE Label5
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 14

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L D0, (Variable14) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 14, Operator 2 -> LiteralOp(value: 4), Store in -> 15

        MOVE.L (Variable14), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable15) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 15), Store in -> 16

        LEA (Variable0), A0
        MOVE.L (Variable15), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable16) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 17

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L D0, (Variable17) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 18

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable18) ; Store local variable
; Instruction of type: SUB
;Operator 1 -> 17, Operator 2 -> ReferenceOp(ref: 18), Store in -> 19

        MOVE.L (Variable17), D0 ; Load local variable
        MOVE.L (Variable18), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L D1, (Variable19) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 19, Operator 2 -> LiteralOp(value: 4), Store in -> 20

        MOVE.L (Variable19), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable20) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 20), Store in -> 21

        LEA (Variable0), A0
        MOVE.L (Variable20), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable21) ; Store local variable
; Instruction of type: IFGT
;Operator 1 -> 16, Operator 2 -> ReferenceOp(ref: 21), Store in -> 0

        MOVE.L (Variable16), D0 ; Load local variable
        MOVE.L (Variable21), D1 ; Load local variable
        CMP.L D1, D0
        BLE Label6
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 22

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable22) ; Store local variable
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label7
; Instruction of type: SKIP
;, Store in -> 0

Label6:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 22

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable22) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label7:
; Instruction of type: IFEQ
;Operator 1 -> 22, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L (Variable22), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BNE Label8
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 23

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINTLN
;Operator 1 -> 23

        LEA        Variable23, A0 ; Cargar la string
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

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 24

        LEA        Variable24, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 25

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L D0, (Variable25) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 25, Operator 2 -> LiteralOp(value: 4), Store in -> 26

        MOVE.L (Variable25), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable26) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 26), Store in -> 27

        LEA (Variable0), A0
        MOVE.L (Variable26), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable27) ; Store local variable
; Instruction of type: PRINT
;Operator 1 -> 27

        MOVE.L (Variable27), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 28

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 28

        LEA        Variable28, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 29

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L D0, (Variable29) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 30

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable30) ; Store local variable
; Instruction of type: SUB
;Operator 1 -> 29, Operator 2 -> ReferenceOp(ref: 30), Store in -> 31

        MOVE.L (Variable29), D0 ; Load local variable
        MOVE.L (Variable30), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L D1, (Variable31) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 31, Operator 2 -> LiteralOp(value: 4), Store in -> 32

        MOVE.L (Variable31), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable32) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 32), Store in -> 33

        LEA (Variable0), A0
        MOVE.L (Variable32), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable33) ; Store local variable
; Instruction of type: PRINTLN
;Operator 1 -> 33

        MOVE.L (Variable33), D1 ; Load local variable
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

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L D0, (Variable34) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 35

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable35) ; Store local variable
; Instruction of type: SUB
;Operator 1 -> 34, Operator 2 -> ReferenceOp(ref: 35), Store in -> 36

        MOVE.L (Variable34), D0 ; Load local variable
        MOVE.L (Variable35), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L D1, (Variable36) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 36, Operator 2 -> LiteralOp(value: 4), Store in -> 37

        MOVE.L (Variable36), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable37) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 37), Store in -> 38

        LEA (Variable0), A0
        MOVE.L (Variable37), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable38) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 38, Store in -> 5

        MOVE.L (Variable38), D0 ; Load local variable
        MOVE.L D0, (Variable5) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 39

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L D0, (Variable39) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 40

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable40) ; Store local variable
; Instruction of type: SUB
;Operator 1 -> 39, Operator 2 -> ReferenceOp(ref: 40), Store in -> 41

        MOVE.L (Variable39), D0 ; Load local variable
        MOVE.L (Variable40), D1 ; Load local variable
        SUBM D0, D1
        MOVE.L D1, (Variable41) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 41, Operator 2 -> LiteralOp(value: 4), Store in -> 42

        MOVE.L (Variable41), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable42) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 43

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L D0, (Variable43) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 43, Operator 2 -> LiteralOp(value: 4), Store in -> 44

        MOVE.L (Variable43), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable44) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 44), Store in -> 45

        LEA (Variable0), A0
        MOVE.L (Variable44), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable45) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 45, Operator 2 -> ReferenceOp(ref: 42), Store in -> 0

        LEA (Variable0), A0
        MOVE.L (Variable42), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (Variable45), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 4, Store in -> 46

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L D0, (Variable46) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 46, Operator 2 -> LiteralOp(value: 4), Store in -> 47

        MOVE.L (Variable46), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable47) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 5, Store in -> 48

        MOVE.L (Variable5), D0 ; Load local variable
        MOVE.L D0, (Variable48) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 48, Operator 2 -> ReferenceOp(ref: 47), Store in -> 0

        LEA (Variable0), A0
        MOVE.L (Variable47), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (Variable48), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 49

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable49) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 49, Store in -> 6

        MOVE.L (Variable49), D0 ; Load local variable
        MOVE.L D0, (Variable6) ; Store local variable
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

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L D0, (Variable50) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 51

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable51) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 50, Operator 2 -> ReferenceOp(ref: 51), Store in -> 52

        MOVE.L (Variable50), D0 ; Load local variable
        MOVE.L (Variable51), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, (Variable52) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 52, Store in -> 4

        MOVE.L (Variable52), D0 ; Load local variable
        MOVE.L D0, (Variable4) ; Store local variable
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

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 53

        LEA        Variable53, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 54

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
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
        MOVE.L D0, (Variable55) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 55, Store in -> 56

        MOVE.L (Variable55), D0 ; Load local variable
        MOVE.L D0, (Variable56) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label10:
; Instruction of type: CLONE
;Operator 1 -> 56, Store in -> 57

        MOVE.L (Variable56), D0 ; Load local variable
        MOVE.L D0, (Variable57) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 3, Store in -> 58

        MOVE.L (Variable3), D0 ; Load local variable
        MOVE.L D0, (Variable58) ; Store local variable
; Instruction of type: IFLT
;Operator 1 -> 57, Operator 2 -> ReferenceOp(ref: 58), Store in -> 0

        MOVE.L (Variable57), D0 ; Load local variable
        MOVE.L (Variable58), D1 ; Load local variable
        CMP.L D1, D0
        BGE Label11
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 59

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable59) ; Store local variable
; Instruction of type: GOTO
;, Store in -> 0

        JMP Label12
; Instruction of type: SKIP
;, Store in -> 0

Label11:
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 59

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable59) ; Store local variable
; Instruction of type: SKIP
;, Store in -> 0

Label12:
; Instruction of type: IFEQ
;Operator 1 -> 59, Operator 2 -> LiteralOp(value: 0), Store in -> 0

        MOVE.L (Variable59), D0 ; Load local variable
        MOVE.L #0, D1 ; Load variable
        CMP.L D1, D0
        BNE Label13
; Instruction of type: CLONE
;Operator 1 -> 56, Store in -> 60

        MOVE.L (Variable56), D0 ; Load local variable
        MOVE.L D0, (Variable60) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 60, Operator 2 -> LiteralOp(value: 4), Store in -> 61

        MOVE.L (Variable60), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable61) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 61), Store in -> 62

        LEA (Variable0), A0
        MOVE.L (Variable61), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable62) ; Store local variable
; Instruction of type: PRINT
;Operator 1 -> 62

        MOVE.L (Variable62), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 63

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 63

        LEA        Variable63, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: ADD
;Operator 1 -> 56, Operator 2 -> LiteralOp(value: 1), Store in -> 64

        MOVE.L (Variable56), D0 ; Load local variable
        MOVE.L #1, D1 ; Load variable
        ADDM D0, D1
        MOVE.L D1, (Variable64) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 64, Store in -> 56

        MOVE.L (Variable64), D0 ; Load local variable
        MOVE.L D0, (Variable56) ; Store local variable
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