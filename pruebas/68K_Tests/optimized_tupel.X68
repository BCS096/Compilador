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
Variable4        DC.B 'koalas',0
Variable5        DC.B 'koalas',0
Variable6        DS.L 8
Variable7        DS.L 1
Variable8        DS.L 1
Variable9        DS.L 8
Variable10        DS.L 1
Variable11        DS.L 1
Variable12        DS.L 1
Variable13        DC.B 'La tupla vale en la i (int): ',0
Variable14        DS.L 1
Variable15        DS.L 1
Variable16        DC.B ' y en la c (char): ',0
Variable17        DS.L 1
Variable18        DS.L 1
Variable19        DS.L 1
Variable20        DS.L 1
Variable21        DC.B 'La tupla vale ahora en la i: ',0
Variable22        DS.L 1
Variable23        DS.L 1
Variable24        DS.L 15
Variable25        DS.L 1
Variable26        DS.L 15
Variable27        DS.L 1
Variable28        DS.L 1
Variable29        DS.L 1
Variable30        DS.L 1
Variable31        DS.L 1
Variable32        DS.L 1
Variable33        DS.L 1
Variable34        DS.L 1
Variable35        DS.L 1
Variable36        DS.L 15
Variable37        DS.L 1
Variable38        DS.L 15
Variable39        DS.L 1
Variable40        DS.L 1
Variable41        DS.L 1
Variable42        DS.L 1
Variable43        DS.L 1
Variable44        DS.L 1
Variable45        DS.L 1
Variable46        DS.L 1
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
;Operator 1 -> 0, Store in -> 0

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable0) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 1

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable1) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 2

        MOVE.L #'c', D0 ; Load variable
        MOVE.L D0, (Variable2) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 3

        MOVE.L #'c', D0 ; Load variable
        MOVE.L D0, (Variable3) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 4

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 5

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 7

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable7) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 8

        MOVE.L #'c', D0 ; Load variable
        MOVE.L D0, (Variable8) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 0), Store in -> 6

        LEA (Variable6), A0
        MOVE.L #0, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L #1, D2 ; Load variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: ASSINDEX
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 4), Store in -> 6

        LEA (Variable6), A0
        MOVE.L #4, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L #'c', D2 ; Load variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 10

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable10) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 11

        MOVE.L #'d', D0 ; Load variable
        MOVE.L D0, (Variable11) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 0), Store in -> 9

        LEA (Variable9), A0
        MOVE.L #0, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L #2, D2 ; Load variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: ASSINDEX
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 4), Store in -> 9

        LEA (Variable9), A0
        MOVE.L #4, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L #'d', D2 ; Load variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 9, Store in -> 12

        LEA Variable9, A0 ; Load local variable
        MOVE.L A0, D0 ; Load local variable
        MOVE.L D0, (Variable12) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 9, Store in -> 6

        LEA Variable9, A0 ; Load local variable
        MOVE.L A0, D0 ; Load local variable
        MOVE.L D0, A0 ; Store local variable
        LEA Variable6, A1 ; Store local variable
        CLR.L D1
        CLR.L D2
        MOVE.L #8, D2 
        DIVS #4, D2 
        MOVE.L D2, D1 
.tupelCopy0:
        MOVE.L (A0)+, (A1)+ ; Store local variable
        DBRA D1, .tupelCopy0
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 13

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 13

        LEA        Variable13, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 14

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable14) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 6, Operator 2 -> LiteralOp(value: 0), Store in -> 15

        LEA (Variable6), A0
        MOVE.L #0, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable15) ; Store local variable
; Instruction of type: PRINT
;Operator 1 -> 15

        MOVE.L (Variable15), D1 ; Load local variable
        MOVE.L #3, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 16

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 16

        LEA        Variable16, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 17

        MOVE.L #4, D0 ; Load variable
        MOVE.L D0, (Variable17) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 6, Operator 2 -> LiteralOp(value: 4), Store in -> 18

        LEA (Variable6), A0
        MOVE.L #4, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable18) ; Store local variable
; Instruction of type: PRINT
;Operator 1 -> 18

        MOVE.L (Variable18), D1 ; Load local variable
        MOVE.L #6, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 19

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable19) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 20

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable20) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 0), Store in -> 6

        LEA (Variable6), A0
        MOVE.L #0, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L #0, D2 ; Load variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 21

        LEA Variable0, A0
        MOVE.L A0, D0 ; Load variable
; Instruction of type: PRINT
;Operator 1 -> 21

        LEA        Variable21, A0 ; Cargar la string
        MOVE.L A0, A1 ; Ready text
        MOVE.L #14, D0 ; Prepare display
        TRAP #15
 ; Expect screen visualization
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 22

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable22) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 6, Operator 2 -> LiteralOp(value: 0), Store in -> 23

        LEA (Variable6), A0
        MOVE.L #0, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable23) ; Store local variable
; Instruction of type: PRINTLN
;Operator 1 -> 23

        MOVE.L (Variable23), D1 ; Load local variable
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

        MOVE.L #5, D0 ; Load variable
        MOVE.L D0, (Variable25) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 25), Store in -> 25

        MOVE.L #4, D0 ; Load variable
        MOVE.L (Variable25), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable25) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 27

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable27) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 27), Store in -> 27

        MOVE.L #4, D0 ; Load variable
        MOVE.L (Variable27), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable27) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 28

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable28) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 29

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable29) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 30

        MOVE.L #5, D0 ; Load variable
        MOVE.L D0, (Variable30) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 0), Store in -> 26

        LEA (Variable26), A0
        MOVE.L #0, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L #5, D2 ; Load variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 31

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable31) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 32

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable32) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 33

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable33) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 34

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable34) ; Store local variable
; Instruction of type: INDVALUE
;Operator 1 -> 26, Operator 2 -> LiteralOp(value: 0), Store in -> 35

        LEA (Variable26), A0
        MOVE.L #0, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (A0), D0 ; D0 = B[C]
        MOVE.L D0, (Variable35) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 35, Operator 2 -> LiteralOp(value: 0), Store in -> 24

        LEA (Variable24), A0
        MOVE.L #0, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L (Variable35), D2 ; Load local variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 37

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable37) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 37), Store in -> 37

        MOVE.L #4, D0 ; Load variable
        MOVE.L (Variable37), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable37) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 39

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable39) ; Store local variable
; Instruction of type: MUL
;Operator 1 -> 0, Operator 2 -> ReferenceOp(ref: 39), Store in -> 39

        MOVE.L #4, D0 ; Load variable
        MOVE.L (Variable39), D1 ; Load local variable
        MULTM D0, D1
        MOVE.L D1, (Variable39) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 40

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable40) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 41

        MOVE.L #0, D0 ; Load variable
        MOVE.L D0, (Variable41) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 42

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable42) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 0), Store in -> 36

        LEA (Variable36), A0
        MOVE.L #0, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L #1, D2 ; Load variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 43

        MOVE.L #1, D0 ; Load variable
        MOVE.L D0, (Variable43) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 44

        MOVE.L #4, D0 ; Load variable
        MOVE.L D0, (Variable44) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 0, Store in -> 45

        MOVE.L #2, D0 ; Load variable
        MOVE.L D0, (Variable45) ; Store local variable
; Instruction of type: ASSINDEX
;Operator 1 -> 0, Operator 2 -> LiteralOp(value: 4), Store in -> 36

        LEA (Variable36), A0
        MOVE.L #4, D0 ; Load variable
        ADD.L D0, A0 ; D0 = @B[C]
        MOVE.L #2, D2 ; Load variable
        MOVE.L D2, (A0) ; Store C IN A[B]
; Instruction of type: CLONE
;Operator 1 -> 36, Store in -> 46

        LEA Variable36, A0 ; Load local variable
        MOVE.L A0, D0 ; Load local variable
        MOVE.L D0, (Variable46) ; Store local variable
; Instruction of type: CLONE
;Operator 1 -> 36, Store in -> 38

        LEA Variable36, A0 ; Load local variable
        MOVE.L A0, D0 ; Load local variable
        MOVE.L D0, A0 ; Store local variable
        LEA Variable38, A1 ; Store local variable
        CLR.L D1
        CLR.L D2
        MOVE.L (Variable39), D2 
        DIVS #4, D2 
        MOVE.L D2, D1 
.arrayCopy0:
        MOVE.L (A0)+, (A1)+ ; Store local variable
        DBRA D1, .arrayCopy0
; Instruction of type: RETURN
;Operator 1 -> 0

        SIMHALT
        END globals