-- Enter your code here. Read input from STDIN. Print output to STDOUT
sticks lst = step lst (len lst)
  where
    p = filter (> 0)
    len = length . p
    cut = minimum . p
    reduce list = map (\x -> x - (cut list)) list
    step list 0 = []
    step list c = c:step (reduce list) (len $ reduce list)

main = do
  n <- readLn :: IO Int
  inputdata <- getContents
  let numbers = take n $ map read $ words inputdata :: [Int]
  putStrLn . unlines $ map show $ sticks numbers