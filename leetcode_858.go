/*
URL := https://leetcode.com/problems/mirror-reflection/
858. Mirror Reflection

Cases :
(A) 9 7 -> 1
(B) 40 12 -> 2
(C) 3 2 -> 0
(D) 400 123
(E) 100 345
(F) 234 165
(G) 948 294



*/
func mirrorReflection(p int, q int) int {
    lcm := LCM(p,q)
    num_cycles := lcm / p
    num_rays := lcm / q
    // fmt.Printf("num cycles = %d\n", num_cycles)
    // fmt.Printf("num_rays = %d\n", num_rays)
    if num_cycles % 2 == 0 {
        return 0
    } else {
        if num_rays % 2 == 1 {
            return 1
        } else {
            return 2
        }
    }
}

// greatest common divisor (GCD) via Euclidean algorithm
func GCD(a, b int) int {
      for b != 0 {
              t := b
              b = a % b
              a = t
      }
      return a
}

// find Least Common Multiple (LCM) via GCD
func LCM(a, b int, integers ...int) int {
      result := a * b / GCD(a, b)

      for i := 0; i < len(integers); i++ {
              result = LCM(result, integers[i])
      }

      return result
}
