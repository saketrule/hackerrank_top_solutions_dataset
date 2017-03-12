import Control.Monad
import Data.Char (ord)

solve :: [Int] -> [Int]
solve case' = reverse $ go case' []
    where 
    go :: [Int] -> [Int] -> [Int]
    go list acc
      | isStop list = acc
      | otherwise   = go (newList) (nCut : acc)
        where
            (newList, nCut) = cut list

cut :: [Int] -> ([Int], Int)
cut list = (filter (\x -> x > 0) $ map (\x -> x -value) list, length list)
    where value = minimum list
    
isStop :: [Int] -> Bool
isStop list = length (filter (\x -> x > 0) list) == 0
    

main :: IO ()
main = do
    _ <- getLine
    elementsLine <- getLine
    let elements = map read $ words elementsLine
    let solutions = solve elements
    mapM_ (putStrLn . show) solutions
    