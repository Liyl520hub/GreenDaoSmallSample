package test.yjj.com.jsontest;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.Fragment;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.SettingService;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class PermissionUtils {


    public static void getPremission(final Context mContext, String permission, final InterfacePermission interfacePermission) {
        AndPermission
                .with(mContext)
                .permission(permission)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        interfacePermission.permissionCallBack();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(mContext, permissions)) {
                            List<String> permissionNames = Permission.transformText(mContext, permissions);
                            final SettingService mSettingService = AndPermission.permissionSetting(mContext);
                            new CustomDialog.Builder(mContext)
                                    .setMessage(PremissionMessageUtils.getTipMessage(permissionNames))
                                    .setTitle("xxxx需要以下权限才能正常运行")
                                    .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }

                                            if (OsHelper.isEMUI()) {
                                                // 帮跳转到该应用的设置界面，让用户手动授权
                                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
                                                intent.setData(uri);
                                                mContext.startActivity(intent);
                                            } else {
                                                mSettingService.execute();
                                            }
                                        }
                                    })
                                    .setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                        }
                                    }).create().show();
                        }
                    }
                }).start();
    }

    public static void getPremission(final Activity activity, String permission, final InterfacePermission interfacePermission) {
        AndPermission
                .with(activity)
                .permission(permission)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        interfacePermission.permissionCallBack();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(activity, permissions)) {
                            List<String> permissionNames = Permission.transformText(activity, permissions);
                            final SettingService mSettingService = AndPermission.permissionSetting(activity);
                            new CustomDialog.Builder(activity)
                                    .setMessage(PremissionMessageUtils.getTipMessage(permissionNames))
                                    .setTitle("xxx需要以下权限才能正常运行")
                                    .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }

                                            if (OsHelper.isEMUI()) {
                                                // 帮跳转到该应用的设置界面，让用户手动授权
                                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                                                intent.setData(uri);
                                                activity.startActivity(intent);
                                            } else {
                                                mSettingService.execute();
                                            }
                                        }
                                    })
                                    .setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                        }
                                    }).create().show();
                        }
                    }
                }).start();
    }

    public static void getPremission(final Fragment fragment, String permission, final InterfacePermission interfacePermission) {
        AndPermission
                .with(fragment)
                .permission(permission)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        interfacePermission.permissionCallBack();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(fragment, permissions)) {
                            List<String> permissionNames = Permission.transformText(fragment.getActivity(), permissions);
                            final SettingService mSettingService = AndPermission.permissionSetting(fragment);
                            new CustomDialog.Builder(fragment.getActivity())
                                    .setMessage(PremissionMessageUtils.getTipMessage(permissionNames))
                                    .setTitle("xxx需要以下权限才能正常运行")
                                    .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }

                                            if (OsHelper.isEMUI()) {
                                                // 帮跳转到该应用的设置界面，让用户手动授权
                                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                Uri uri = Uri.fromParts("package", MyApplication.getContext().getPackageName(), null);
                                                intent.setData(uri);
                                                fragment.startActivity(intent);
                                            } else {
                                                mSettingService.execute();
                                            }
                                        }
                                    })
                                    .setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                        }
                                    }).create().show();
                        }
                    }
                }).start();
    }


    public static void getPremissions(final Context mContext, String[] permissions, final InterfacePermission interfacePermission) {

        AndPermission
                .with(mContext)
                .permission(permissions)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        interfacePermission.permissionCallBack();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(mContext, permissions)) {
                            List<String> permissionNames = Permission.transformText(mContext, permissions);
                            final SettingService mSettingService = AndPermission.permissionSetting(mContext);
                            new CustomDialog.Builder(mContext)
                                    .setMessage(PremissionMessageUtils.getTipMessage(permissionNames))
                                    .setTitle("xxx需要以下权限才能正常运行")
                                    .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                            if (OsHelper.isEMUI()) {
                                                // 帮跳转到该应用的设置界面，让用户手动授权
                                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
                                                intent.setData(uri);
                                                mContext.startActivity(intent);
                                            } else {
                                                mSettingService.execute();
                                            }
                                        }
                                    })
                                    .setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                        }
                                    }).create().show();
                        }
                    }
                }).start();
    }

    public static void getPremissions(final Activity activity, String[] permissions, final InterfacePermission interfacePermission) {

        AndPermission
                .with(activity)
                .permission(permissions)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        interfacePermission.permissionCallBack();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(activity, permissions)) {
                            List<String> permissionNames = Permission.transformText(activity, permissions);
                            final SettingService mSettingService = AndPermission.permissionSetting(activity);
                            new CustomDialog.Builder(activity)
                                    .setMessage(PremissionMessageUtils.getTipMessage(permissionNames))
                                    .setTitle("xxxx需要以下权限才能正常运行")
                                    .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                            if (OsHelper.isEMUI()) {
                                                // 帮跳转到该应用的设置界面，让用户手动授权
                                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                                                intent.setData(uri);
                                                activity.startActivity(intent);
                                            } else {
                                                mSettingService.execute();
                                            }
                                        }
                                    })
                                    .setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                        }
                                    }).create().show();
                        }
                    }
                }).start();
    }

    public static void getPremissions(final Fragment fragment, String[] permissions, final InterfacePermission interfacePermission) {

        AndPermission
                .with(fragment)
                .permission(permissions)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        interfacePermission.permissionCallBack();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(fragment, permissions)) {
                            List<String> permissionNames = Permission.transformText(fragment.getActivity(), permissions);
                            final SettingService mSettingService = AndPermission.permissionSetting(fragment);
                            new CustomDialog.Builder(fragment.getActivity())
                                    .setMessage(PremissionMessageUtils.getTipMessage(permissionNames))
                                    .setTitle("xxxx需要以下权限才能正常运行")
                                    .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                            if (OsHelper.isEMUI()) {
                                                // 帮跳转到该应用的设置界面，让用户手动授权
                                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                Uri uri = Uri.fromParts("package", MyApplication.getContext().getPackageName(), null);
                                                intent.setData(uri);
                                                fragment.startActivity(intent);
                                            } else {
                                                mSettingService.execute();
                                            }
                                        }
                                    })
                                    .setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                        }
                                    }).create().show();
                        }
                    }
                }).start();
    }


    public interface InterfacePermission {

        /**
         * 申请权限成功后的回调处理
         */
        void permissionCallBack();

    }


}
