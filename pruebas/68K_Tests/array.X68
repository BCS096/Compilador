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
Variable28        DS.L 1
Variable29        DC.B 'Copia contiene:',0
Variable30        DS.L 1
Variable31        DS.L 1
Variable32        DS.L 1
Variable33        DC.B 'Original tenia:',0
Variable34        DS.L 1
Variable35        DS.L 1
Variable36        DS.L 1
Variable37        DS.L 15
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
Variable51        DC.B 'Arrays multidimensionales?',0
Variable52        DS.L 1
Variable53        DS.L 1
Variable54        DS.L 1
Variable55        DS.L 1
Variable56        DS.L 1
Variable57        DS.L 1
Variable58        DS.L 1
Variable59        DS.L 1
Variable60        DC.B 'Ahora a ver la copia',0
Variable61        DS.L 15
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

        MOVE.L #5, D0 ; Load variable
        MOVE.L D0, (Variable1) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 1), Store in -> 1

        MOVE.L #4, D0 ; Load variable
        MOVE.L (Variable1), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable1) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 3

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable3) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 3), Store in -> 3

        MOVE.L #4, D0 ; Load variable
        MOVE.L (Variable3), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable3) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 4

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable4) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 4, Operator 2 -> LiteralOp(value: 4), Store in -> 5

        MOVE.L (Variable4), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable5) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 6

        MOVE.L #5, D0 ; Load variable
        MOVE.L D0, (Variable6) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 6, Operator 2 -> ReferenceOp(ref: 5), Store in -> 2

        LEA (Variable2), A0
        MOVE.L (Variable5), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (Variable6), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable7) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 7, Operator 2 -> LiteralOp(value: 4), Store in -> 8

        MOVE.L (Variable7), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable8) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 2, Operator 2 -> ReferenceOp(ref: 8), Store in -> 9

        LEA (Variable2), A0
        MOVE.L (Variable8), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable9) ; Store local variable
; Instruction of type: PRINT
;Operator 1 -> 9

        MOVE.L (Variable9), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 10

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable10) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 10, Operator 2 -> LiteralOp(value: 4), Store in -> 11

        MOVE.L (Variable10), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable11) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 12

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable12) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 12, Operator 2 -> LiteralOp(value: 4), Store in -> 13

        MOVE.L (Variable12), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable13) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 2, Operator 2 -> ReferenceOp(ref: 13), Store in -> 14

        LEA (Variable2), A0
        MOVE.L (Variable13), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable14) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 14, Operator 2 -> ReferenceOp(ref: 11), Store in -> 0

        LEA (Variable0), A0
        MOVE.L (Variable11), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (Variable14), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 16

        MOVE.L #3, D0 ; Load variable
        MOVE.L D0, (Variable16) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 16), Store in -> 16

        MOVE.L #4, D0 ; Load variable
        MOVE.L (Variable16), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable16) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 18

        MOVE.L #3, D0 ; Load variable
        MOVE.L D0, (Variable18) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 18), Store in -> 18

        MOVE.L #4, D0 ; Load variable
        MOVE.L (Variable18), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable18) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 19

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable19) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 19, Operator 2 -> LiteralOp(value: 4), Store in -> 20

        MOVE.L (Variable19), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable20) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 21

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable21) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 21, Operator 2 -> ReferenceOp(ref: 20), Store in -> 15

        LEA (Variable15), A0
        MOVE.L (Variable20), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (Variable21), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 22

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable22) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 22, Operator 2 -> LiteralOp(value: 4), Store in -> 23

        MOVE.L (Variable22), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable23) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 24

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable24) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 24, Operator 2 -> ReferenceOp(ref: 23), Store in -> 15

        LEA (Variable15), A0
        MOVE.L (Variable23), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (Variable24), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 25

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable25) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 25, Operator 2 -> LiteralOp(value: 4), Store in -> 26

        MOVE.L (Variable25), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable26) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 27

        MOVE.L #3, D0 ; Load variable
        MOVE.L D0, (Variable27) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 27, Operator 2 -> ReferenceOp(ref: 26), Store in -> 15

        LEA (Variable15), A0
        MOVE.L (Variable26), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (Variable27), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 15, Store in -> 28

        LEA Variable15, A0 ; Load local variable
        MOVE.L A0, D0 ; Load local variable
        MOVE.L D0, (Variable28) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 28, Store in -> 17

        MOVE.L (Variable28), D0 ; Load local variable
        MOVE.L D0, (A0) ; Store local variable
        LEA Variable17, A1 ; Store local variable
        MOVE.L 0(A0), 0(A1) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 29

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 29

        LEA        Variable29, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 30

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable30) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 30, Operator 2 -> LiteralOp(value: 4), Store in -> 31

        MOVE.L (Variable30), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable31) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 17, Operator 2 -> ReferenceOp(ref: 31), Store in -> 32

        LEA (Variable17), A0
        MOVE.L (Variable31), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable32) ; Store local variable
; Instruction of type: PRINTLN
;Operator 1 -> 32

        MOVE.L (Variable32), D1 ; Load local variable
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
;Operator 1 -> 0, Store in -> 33

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 33

        LEA        Variable33, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 34

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable34) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 34, Operator 2 -> LiteralOp(value: 4), Store in -> 35

        MOVE.L (Variable34), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable35) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 15, Operator 2 -> ReferenceOp(ref: 35), Store in -> 36

        LEA (Variable15), A0
        MOVE.L (Variable35), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable36) ; Store local variable
; Instruction of type: PRINTLN
;Operator 1 -> 36

        MOVE.L (Variable36), D1 ; Load local variable
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
;Operator 1 -> 0, Store in -> 38

        MOVE.L #3, D0 ; Load variable
        MOVE.L D0, (Variable38) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 39

        MOVE.L #3, D0 ; Load variable
        MOVE.L D0, (Variable39) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 40

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable40) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 40, Operator 2 -> ReferenceOp(ref: 39), Store in -> 41

        MOVE.L (Variable40), D0 ; Load local variable
        MOVE.L (Variable39), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, (Variable41) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 41, Operator 2 -> ReferenceOp(ref: 38), Store in -> 42

        MOVE.L (Variable41), D0 ; Load local variable
        MOVE.L (Variable38), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, (Variable42) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 42), Store in -> 42

        MOVE.L #4, D0 ; Load variable
        MOVE.L (Variable42), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable42) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 43

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable43) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 43, Store in -> 44

        MOVE.L (Variable43), D0 ; Load local variable
        MOVE.L D0, (Variable44) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 45

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable45) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 44, Operator 2 -> ReferenceOp(ref: 39), Store in -> 46

        MOVE.L (Variable44), D0 ; Load local variable
        MOVE.L (Variable39), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable46) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 46, Operator 2 -> ReferenceOp(ref: 45), Store in -> 47

        MOVE.L (Variable46), D0 ; Load local variable
        MOVE.L (Variable45), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, (Variable47) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 48

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable48) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 47, Operator 2 -> LiteralOp(value: 4), Store in -> 49

        MOVE.L (Variable47), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable49) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 50

        MOVE.L #69, D0 ; Load variable
        MOVE.L D0, (Variable50) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 50, Operator 2 -> ReferenceOp(ref: 49), Store in -> 37

        LEA (Variable37), A0
        MOVE.L (Variable49), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (Variable50), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 51

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINTLN
;Operator 1 -> 51

        LEA        Variable51, A0 ; Cargar la string
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
;Operator 1 -> 0, Store in -> 52

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable52) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 52, Store in -> 53

        MOVE.L (Variable52), D0 ; Load local variable
        MOVE.L D0, (Variable53) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 54

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable54) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 53, Operator 2 -> ReferenceOp(ref: 39), Store in -> 55

        MOVE.L (Variable53), D0 ; Load local variable
        MOVE.L (Variable39), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable55) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 55, Operator 2 -> ReferenceOp(ref: 54), Store in -> 56

        MOVE.L (Variable55), D0 ; Load local variable
        MOVE.L (Variable54), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, (Variable56) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 57

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable57) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 56, Operator 2 -> LiteralOp(value: 4), Store in -> 58

        MOVE.L (Variable56), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable58) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 37, Operator 2 -> ReferenceOp(ref: 58), Store in -> 59

        LEA (Variable37), A0
        MOVE.L (Variable58), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable59) ; Store local variable
; Instruction of type: PRINT
;Operator 1 -> 59

        MOVE.L (Variable59), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 60

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINTLN
;Operator 1 -> 60

        LEA        Variable60, A0 ; Cargar la string
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
;Operator 1 -> 0, Store in -> 62

        MOVE.L #3, D0 ; Load variable
        MOVE.L D0, (Variable62) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 63

        MOVE.L #3, D0 ; Load variable
        MOVE.L D0, (Variable63) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 64

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable64) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 64, Operator 2 -> ReferenceOp(ref: 63), Store in -> 65

        MOVE.L (Variable64), D0 ; Load local variable
        MOVE.L (Variable63), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, (Variable65) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 65, Operator 2 -> ReferenceOp(ref: 62), Store in -> 66

        MOVE.L (Variable65), D0 ; Load local variable
        MOVE.L (Variable62), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, (Variable66) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 66), Store in -> 66

        MOVE.L #4, D0 ; Load variable
        MOVE.L (Variable66), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable66) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 37, Store in -> 67

        LEA Variable37, A0 ; Load local variable
        MOVE.L A0, D0 ; Load local variable
        MOVE.L D0, (Variable67) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 67, Store in -> 61

        MOVE.L (Variable67), D0 ; Load local variable
        MOVE.L D0, (A0) ; Store local variable
        LEA Variable61, A1 ; Store local variable
        MOVE.L 0(A0), 0(A1) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 68

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable68) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 68, Store in -> 69

        MOVE.L (Variable68), D0 ; Load local variable
        MOVE.L D0, (Variable69) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 70

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable70) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 69, Operator 2 -> ReferenceOp(ref: 63), Store in -> 71

        MOVE.L (Variable69), D0 ; Load local variable
        MOVE.L (Variable63), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable71) ; Store local variable
; Instruction of type: ADD
;Operator 1 -> 71, Operator 2 -> ReferenceOp(ref: 70), Store in -> 72

        MOVE.L (Variable71), D0 ; Load local variable
        MOVE.L (Variable70), D1 ; Load local variable
        ADDM D0, D1
        MOVE.L D1, (Variable72) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 73

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable73) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 72, Operator 2 -> LiteralOp(value: 4), Store in -> 74

        MOVE.L (Variable72), D0 ; Load local variable
        MOVE.L #4, D1 ; Load variable
        MULTM D0, D1
        MOVE.L D1, (Variable74) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 61, Operator 2 -> ReferenceOp(ref: 74), Store in -> 75

        LEA (Variable61), A0
        MOVE.L (Variable74), D0 ; Load local variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable75) ; Store local variable
; Instruction of type: PRINTLN
;Operator 1 -> 75

        MOVE.L (Variable75), D1 ; Load local variable
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