import Data.List

cut::[Int]->[Int]
cut all@(x:xs) = [length all] ++ (cut filtered)
    where filtered = filter (\i -> i > x) all
cut (x:[]) = [1]
cut [] = []

readInt::String -> Int
readInt x = read x::Int

main = do 
    _ <- getLine
    line <- getLine
    let ints = sort $ map readInt (words line)
    mapM print (cut ints)