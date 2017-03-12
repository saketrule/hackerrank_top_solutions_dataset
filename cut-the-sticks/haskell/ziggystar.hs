solve :: [Int] -> [Int]
solve x = reverse $ solveR x []
    where solveR [] acc = acc
          solveR xs acc = solveR cut (length xs : acc)
            where m = minimum xs
                  cut = filter (>0) . map (subtract m) $ xs
main = do
    _ <- getLine
    sticks <- fmap  (fmap read) . fmap words $ getLine :: IO [Int]
    mapM_ (putStrLn.show) (solve sticks)