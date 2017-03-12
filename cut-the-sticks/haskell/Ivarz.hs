type Sticks = [Int]
type Cuts = Int
type CutsAndSticks = (Cuts, Sticks)


rmFstLn :: String -> [String]
rmFstLn x= drop 1 $ lines x

toSticks :: [String] -> Sticks
toSticks x = map (\i->read(i)::Int) $ words $ head x

cutEm :: Sticks -> Sticks
cutEm x = filter (>0) $ map (\i->i-(minimum x)) x

getCuts :: Sticks -> Cuts
getCuts x= length $ filter (>0) x

mcWrap :: Sticks -> CutsAndSticks
mcWrap x = (1,x)

oneCutAndSticks:: CutsAndSticks -> CutsAndSticks
oneCutAndSticks (y,x) = (getCuts x, cutEm x)

cutAll :: CutsAndSticks -> [CutsAndSticks]
cutAll x= drop 1$ takeWhile (\(i,x) -> i > 0) $ iterate oneCutAndSticks x

pickCuts :: [CutsAndSticks] -> [Cuts]
pickCuts x = map fst x

main = do
	contents<-getContents
	putStrLn $ unlines$ map show $ pickCuts $ cutAll $ mcWrap $ toSticks $ rmFstLn contents

