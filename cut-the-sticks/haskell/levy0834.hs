import Data.List
import Control.Monad
import Text.Printf
import Data.Array.IO
import Data.Int
import Data.Char
import qualified Data.Map as M
import Data.Function

main = do
  n <- readLn :: IO Int
  a <- fmap (map (read :: String -> Int) . words) getLine
  let mp = init $ reverse $ foldl (\acc x -> (head acc - x) : acc) [n] $ map snd $ sortBy (compare `on` fst) $ M.toList $ foldl (\acc (x,y)-> M.insertWith (+) x y acc) M.empty $ zip a [1,1..]
  putStrLn $ unlines $ map show mp