package cn.zptc.blog.service.serviceImpl;

import cn.zptc.blog.dao.TypeDao;
import cn.zptc.blog.entity.Type;
import cn.zptc.blog.exception.NotFoundException;
import cn.zptc.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public int saveType(Type type) {
       return typeDao.saveType(type);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeDao.getTypeById(id);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Transactional
    @Override
    public int updateType(Long id,Type type) {
        Type t= typeDao.getTypeById(id);
        if(t==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
       return typeDao.updateType(t);
    }

    @Override
    public List<Type> listType() {
        return typeDao.listType();
    }

    @Override
    public List<Type> listTypeTop() {
        return typeDao.listTypeTop();
    }

    @Override
    public List<Type> listTypeOrderByCount() {
        return typeDao.listTypeOrderByCount();
    }
}
