import Data.List

solve :: [Int] -> [Int]
solve = reverse . scanl1 (+) . map length . group . sortBy (flip compare)

main = mapM_ print . solve . parse . lines =<< getContents
  where parse (x:y:_) = map read $ take n $ words y where n = read x
