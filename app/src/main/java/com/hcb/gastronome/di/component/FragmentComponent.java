package com.hcb.gastronome.di.component;

import com.hcb.gastronome.di.modules.FragmentModule;
import com.hcb.gastronome.di.scope.FragmentScope;
import com.hcb.gastronome.ui.fragment.HomeFragment;
import com.hcb.gastronome.ui.fragment.delicious.DeliciousFragment;
import com.hcb.gastronome.ui.fragment.delicious.DishesFragment;
import com.hcb.gastronome.ui.fragment.mine.CollectionFragment;
import com.hcb.gastronome.ui.fragment.user.LoginFragment;
import com.hcb.gastronome.ui.fragment.user.RegisterFragment;

import dagger.Component;

/**
 * Created by huchuanbin on 16/3/29.
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(DeliciousFragment deliciousFragment);

    void inject(DishesFragment dishesFragment);

    void inject(RegisterFragment registerFragment);

    void inject(LoginFragment loginFragment);

    void inject(HomeFragment homeFragment);

    void inject(CollectionFragment collectionFragment);
}
