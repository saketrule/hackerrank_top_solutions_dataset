import Data.List

solve :: (Integral a) => [a] -> [a]
solve x = (fst . f) $ map (fromIntegral . length) x'
  where x' = (group . sort) x
        f = foldr (\x1 (ll, a) -> ((x1+a):ll, a+x1)) ([], 0)

main :: IO ()
main = do
  g <- getContents
  let inp = map (\x -> read x :: Int)  $ words $ lines g !! 1
  putStr (unlines $ map show $ solve inp)