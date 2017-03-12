-- Enter your code here. Read input from STDIN. Print output to STDOUT
import Data.List (sort)

solve :: [Int] -> [Int]
solve = aux [] . sort
    where 
          aux acc [] = acc
          aux acc l = aux ((length l) : acc) $ collapse l
          collapse [] = []
          collapse (x:xs) = filter (>x) xs
          
main = getLine >> getLine >>= mapM_ print . reverse . solve . map read . words