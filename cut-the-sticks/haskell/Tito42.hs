-- Enter your code here. Read input from STDIN. Print output to STDOUT

f :: [Int] -> IO ()
f [] = return ()
f xs = do
    putStrLn . show . length $ xs
    let m   = minimum xs
        xs' = filter (>0) . map (subtract m) $ xs
    f xs'

main = do
 getLine
 getLine >>= return . map read . words >>= f