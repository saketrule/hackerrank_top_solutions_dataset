-- Enter your code here. Read input from STDIN. Print output to STDOUT
import Control.Monad
f nums acc | null nums = reverse acc
           | otherwise = f (filter (>0) (map (\x->x-(minimum nums)) nums)) ([length nums]++acc)
main = do
    getLine
    line <- getLine
    let nums = map read $ words line :: [Int]
        ans  = f nums []
    mapM_ print ans