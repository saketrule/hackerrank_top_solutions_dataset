next :: [Int] -> [Int]
next a = filter (>0) (map (subtract l) a)
    where l = minimum a

solve = map length . evolution next []

evolution :: (Eq a) => (a -> a) -> a -> a -> [a]
evolution f end x | x == end = []
                  | otherwise = x : (evolution f end (f x))

main = do
    l <- getLine
    lb <- getLine
    mapM_ print (solve (map (read) (words lb)))