package com.ryu.minecraft.mod.neoforge.neovillagers.designer.helpers;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DecoratorHelper {
    
    public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
        final VoxelShape[] buffer = new VoxelShape[] { shape, Shapes.empty() };
        
        final int times = ((to.get2DDataValue() - from.get2DDataValue()) + 4) % 4;
        
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.join(buffer[1],
                    Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX), BooleanOp.OR));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }
        
        return buffer[0];
    }
    
    public static VoxelShape rotateVertical(VoxelShape shape, boolean upsideDown) {
        final VoxelShape[] out = new VoxelShape[] { Shapes.empty() };
        
        shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> {
            final double nMinY = upsideDown ? 1 - maxY : minZ;
            final double nMaxY = upsideDown ? 1 - minY : maxZ;
            final double nMinZ = upsideDown ? minZ : minY;
            final double nMaxZ = upsideDown ? maxZ : maxY;
            
            out[0] = Shapes.join(out[0], Shapes.box(minX, nMinY, nMinZ, maxX, nMaxY, nMaxZ), BooleanOp.OR);
        });
        
        return out[0];
    }
    
    private DecoratorHelper() {
    }
}
