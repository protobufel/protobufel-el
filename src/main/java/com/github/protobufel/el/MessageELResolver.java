//
// Copyright © 2014, David Tesler (https://github.com/protobufel)
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
// * Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer in the
// documentation and/or other materials provided with the distribution.
// * Neither the name of the <organization> nor the
// names of its contributors may be used to endorse or promote products
// derived from this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
// DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
// ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
// SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//

package com.github.protobufel.el;

import javax.el.ELContext;
import javax.el.ELResolver;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;

/**
 * ProtoBuf Message ELResolver.
 *
 * @see ELResolver
 * @author protobufel@gmail.com David Tesler
 */
public class MessageELResolver extends BuilderELResolver {

  public MessageELResolver() {
    this(false);
  }

  public MessageELResolver(final boolean isStrictMode) {
    super(true, isStrictMode);
  }

  @Override
  protected boolean resolveType(final Object base) {
    return base instanceof Message;
  }

  @Override
  public Object getValue(final ELContext context, final Object base, final Object property) {
    if (context == null) {
      throw new NullPointerException();
    }

    if ((property != null) && (base instanceof Message)) {
      context.setPropertyResolved(base, property);
      final Message msg = (Message) base;
      final FieldDescriptor field = getPropertyFieldDescriptor(msg, property);
      return msg.getField(field);
    }

    return null;
  }
}
