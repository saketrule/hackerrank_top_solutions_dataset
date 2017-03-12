module Main where

import Data.Maybe

main :: IO ()
main = do
    input <- getContents
    
    mapM_ (mapM_ (putStrLn . show))
        $ catMaybes $ map doCuts (lines input)

doCuts :: String -> Maybe [Int]
doCuts s
  | length ns == 1 = Nothing
  | otherwise =
    let
      doCuts' :: [Maybe Int] -> [Int]
      doCuts' ints =
        if done then [] else nCuts : doCuts' next
        where next = map (doCut smallest) ints
              nCuts = (length . catMaybes) ints - ((length (catMaybes ints)) - (length (catMaybes next)))
              done = all isNothing next
              smallest = minimum (catMaybes ints)
    in
      Just $ (length ns) : doCuts' (map Just ns)
  where ns       = map read (words s)

doCut :: Int -> Maybe Int -> Maybe Int
doCut _ Nothing = Nothing
doCut cut (Just n) = if (n > cut) then Just (n - cut) else Nothing