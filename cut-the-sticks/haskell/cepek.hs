solve :: [Integer] -> [Int]
solve [] = []
solve xs = length xs : solve reduced where
	m = minimum xs
	reduced = filter (>0) $ map (\e -> e - m) xs

main :: IO ()
main = getContents >>= mapM_ print . solve . map (read :: String -> Integer) . tail . words