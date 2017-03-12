import Data.List.Split (splitOn)

cut :: [Int] -> [Int]
cut xs = filter (> 0) . map (flip (-) (minimum xs)) $ xs

main = do
	n <- getLine
	inputs <- return . map read . splitOn " " =<< getLine
	mapM_ print $ map length . takeWhile (not . null) . iterate cut $ inputs