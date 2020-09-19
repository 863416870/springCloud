package cc.young.common.util;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.util.Assert;

import java.util.Collection;

public class CollectionBeanUtil {

    public static <I,O> void copyCollectionProperties(Collection<I> source,Collection<O> target, Class<O> classO ){
        Assert.notNull(source);
        Assert.notNull(target);

        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        //深拷贝
        source.forEach(t ->{
            O vo = mapper.map(t, classO);
            target.add(vo);
        });
    }

}
