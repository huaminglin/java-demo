package huaminglin.demo.datatype.ognl;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Map;
import ognl.MemberAccess;

public class DefaultMemberAccess implements MemberAccess {

  public boolean allowPrivateAccess;
  public boolean allowProtectedAccess;
  public boolean allowPackageProtectedAccess;

  /*===================================================================
    Constructors
    ===================================================================*/
  public DefaultMemberAccess(boolean allowAllAccess) {
    this(allowAllAccess, allowAllAccess, allowAllAccess);
  }

  public DefaultMemberAccess(boolean allowPrivateAccess, boolean allowProtectedAccess,
      boolean allowPackageProtectedAccess) {
    super();
    this.allowPrivateAccess = allowPrivateAccess;
    this.allowProtectedAccess = allowProtectedAccess;
    this.allowPackageProtectedAccess = allowPackageProtectedAccess;
  }

  /*===================================================================
    Public methods
    ===================================================================*/
  public boolean getAllowPrivateAccess() {
    return allowPrivateAccess;
  }

  public void setAllowPrivateAccess(boolean value) {
    allowPrivateAccess = value;
  }

  public boolean getAllowProtectedAccess() {
    return allowProtectedAccess;
  }

  public void setAllowProtectedAccess(boolean value) {
    allowProtectedAccess = value;
  }

  public boolean getAllowPackageProtectedAccess() {
    return allowPackageProtectedAccess;
  }

  public void setAllowPackageProtectedAccess(boolean value) {
    allowPackageProtectedAccess = value;
  }

  /*===================================================================
    MemberAccess interface
    ===================================================================*/
  @Override
  public Object setup(Map context, Object target, Member member, String propertyName) {
    Object result = null;

    if (isAccessible(context, target, member, propertyName)) {
      AccessibleObject accessible = (AccessibleObject) member;

      if (!accessible.isAccessible()) {
        result = Boolean.FALSE;
        accessible.setAccessible(true);
      }
    }
    return result;
  }

  @Override
  public void restore(Map context, Object target, Member member, String propertyName,
      Object state) {
    if (state != null) {
      ((AccessibleObject) member).setAccessible(((Boolean) state).booleanValue());
    }
  }

  /**
   * Returns true if the given member is accessible or can be made accessible by this object.
   */
  @Override
  public boolean isAccessible(Map context, Object target, Member member, String propertyName) {
    int modifiers = member.getModifiers();
    boolean result = Modifier.isPublic(modifiers);

    if (!result) {
      if (Modifier.isPrivate(modifiers)) {
        result = getAllowPrivateAccess();
      } else {
        if (Modifier.isProtected(modifiers)) {
          result = getAllowProtectedAccess();
        } else {
          result = getAllowPackageProtectedAccess();
        }
      }
    }
    return result;
  }
}
